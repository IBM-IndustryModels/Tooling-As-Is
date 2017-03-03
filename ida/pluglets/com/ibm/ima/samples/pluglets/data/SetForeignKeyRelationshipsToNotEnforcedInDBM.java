/* 
 * Licensed Materials - Property of IBM
 * 
 * 5748-XX8
 * 
 * © Copyright IBM Corp. 2017
 *
 */

package com.ibm.ima.samples.pluglets.data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.io.FileNotFoundException;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import com.ibm.datatools.core.DataToolsPlugin;
import com.ibm.datatools.pluglets.ui.DataPluglet;
import com.ibm.datatools.pluglets.ui.DataPlugletTaskGroup;
import com.ibm.db.models.logical.Attribute;
import com.ibm.db.models.logical.Relationship;
import com.ibm.xtools.pluglets.Pluglet;

/**
 * The purpose of this pluglet is to set the Enforced flag to false for all
 * Foreign Keys in a physical data model
 */

public class SetForeignKeyRelationshipsToNotEnforcedInDBM extends Pluglet implements DataPluglet {

	public static final String copyright = "(c) Copyright IBM Corporation 2017."; //$NON-NLS-1$

	private static final DataPlugletTaskGroup[] taskGroups = new DataPlugletTaskGroup[] { DataPlugletTaskGroup.CUSTOM_TASKS };

	// Variable to keep count of changed foreign keys
	private int count;

	@Override
	public String getName() {
		return "Set Foreign Key Relationships To Not Enforced in Physical Data Model";
	}

	@Override
	public DataPlugletTaskGroup[] getTaskGroups() {
		return taskGroups;
	}

	@Override
	public boolean isValidSelection(ISelection selection) {
		if (selection instanceof StructuredSelection) {
			List<?> selectionList = ((StructuredSelection) selection).toList();
			for (Object object : selectionList) {
				if (!(object instanceof SQLObject)) {
					return false;
				}
			}
			// all objects are SQLObjects
			return true;
		}
		// we could not verify the workspace selection
		return false;
	}

	/**
	 * @return the current workspace selection
	 */
	private ISelection getWorkspaceSelection() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
	}

	/**
	 * The pluglet's main method will be invoked when the pluglet is run.
	 * 
	 * @param args
	 */
	public void plugletmain(String[] args) throws FileNotFoundException {
		count = 0;
		// get the workspace selection
		final ISelection workspaceSelection = getWorkspaceSelection();

		// verify this pluglet can run on the selected objects
		if (!isValidSelection(workspaceSelection)) {
			inform("Current workspace selection is not valid for pluglet"); //$NON-NLS-1$
			return;
		} else {
			//
		}
		TransactionalEditingDomain editingDomain = DataToolsPlugin.getDefault().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			protected void doExecute() {
				Iterator<?> selectionListIter = ((StructuredSelection) workspaceSelection).toList().iterator();
				while (selectionListIter.hasNext()) {
					Object nxtObj = selectionListIter.next();
					if (nxtObj != null) {
						if (nxtObj instanceof SQLObject) {
							SQLObject sqlObj = (SQLObject) nxtObj;
							SetForeignKeyRelationshipsToNotEnforcedInDBM(sqlObj);
						}
					}
				}
				inform("Number of relationships updated : " + count);
			}

			@SuppressWarnings("unchecked")
			private void SetForeignKeyRelationshipsToNotEnforcedInDBM(SQLObject sqlObject) {
				// correct enforced flag on FK's if instance of table
				if (sqlObject instanceof Table) {
					switchEnforcedOff(sqlObject);
				}
				// get tables if instance of schema
				else if (sqlObject instanceof Schema) {
					List<?> validChildren = new LinkedList<Object>();
					validChildren.addAll(((Schema) sqlObject).getTables());
					Iterator<?> validChildrenIter = validChildren.iterator();
					while (validChildrenIter.hasNext()) {
						Object nextObj = validChildrenIter.next();
						if (nextObj instanceof SQLObject) {
							SetForeignKeyRelationshipsToNotEnforcedInDBM((SQLObject) nextObj);
						}
					}
					// correct enforced flag on FK's if instance of table
					switchEnforcedOff(sqlObject);
				} else if (sqlObject instanceof Database) {
					List<?> validChildren = new LinkedList<Object>();
					validChildren.addAll(((Database) sqlObject).getSchemas());
					Iterator<?> validChildrenIter = validChildren.iterator();
					while (validChildrenIter.hasNext()) {
						Object nextObj = validChildrenIter.next();
						if (nextObj instanceof SQLObject) {
							SetForeignKeyRelationshipsToNotEnforcedInDBM((SQLObject) nextObj);
						}
					}
				}
				if (sqlObject instanceof Attribute || sqlObject instanceof Relationship) {
					switchEnforcedOff(sqlObject);
				}
			}

			private void switchEnforcedOff(SQLObject sqlObject) {
				if (sqlObject instanceof BaseTable) {
					BaseTable table = (BaseTable) sqlObject;
					Schema schema = table.getSchema();
					Iterator<?> tableIter = schema.getTables().iterator();
					// Iterate through schema and get all tables
					while (tableIter.hasNext()) {
						BaseTable nxtTable = (BaseTable) tableIter.next();
						Iterator<?> FKIter = nxtTable.getForeignKeys().iterator();
						// Iterate through tables and get the all foreign keys
						while (FKIter.hasNext()) {
							ForeignKey fKey = (ForeignKey) FKIter.next();
							if (fKey.isEnforced()) {
								// Sets the enforced flag on a foreign key to
								// false if set to true
								fKey.setEnforced(!fKey.isEnforced());
								count++;
							}
						}
					}
				}
			}
		});
	}

}
