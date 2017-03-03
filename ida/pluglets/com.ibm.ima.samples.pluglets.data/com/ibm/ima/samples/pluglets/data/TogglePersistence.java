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
import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import com.ibm.datatools.core.DataToolsPlugin;
import com.ibm.datatools.pluglets.ui.DataPluglet;
import com.ibm.datatools.pluglets.ui.DataPlugletTaskGroup;
import com.ibm.db.models.logical.Attribute;
import com.ibm.db.models.logical.Entity;
import com.ibm.db.models.logical.Relationship;
import com.ibm.xtools.pluglets.Pluglet;

public class TogglePersistence extends Pluglet implements DataPluglet {

	public static final String copyright = "(c) Copyright IBM Corporation 2017."; //$NON-NLS-1$

	private static final DataPlugletTaskGroup[] taskGroups = new DataPlugletTaskGroup[] { DataPlugletTaskGroup.MODEL_TASKS };

	@Override
	public String getName() {
		return "Toggle Persistence of Selected Elements";
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
			// all selected objects were View representations of SQLObjects
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
	public void plugletmain(String[] args) {

		// get the workspace selection
		final ISelection workspaceSelection = getWorkspaceSelection();

		// verify this pluglet can run on the selected objects
		if (!isValidSelection(workspaceSelection)) {
			inform("Current workspace selection is not valid for pluglet"); //$NON-NLS-1$
			return;
		} else {
			inform("Current workspace selection is valid for pluglet"); //$NON-NLS-1$
		}
		TransactionalEditingDomain editingDomain = DataToolsPlugin.getDefault().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			protected void doExecute() {
				Iterator<?> selectionListIter = ((StructuredSelection) workspaceSelection).toList().iterator();
				while (selectionListIter.hasNext()) {
					Object nxtObj = selectionListIter.next();
					if (nxtObj != null) {
						if (nxtObj instanceof Attribute) {
							Attribute attribute = (Attribute) nxtObj;
							attribute.setPersistent(!attribute.isPersistent());
						} else if (nxtObj instanceof Entity) {
							Entity entity = (Entity) nxtObj;
							entity.setPersistent(!entity.isPersistent());
						} else if (nxtObj instanceof Relationship) {
							Relationship relationship = (Relationship) nxtObj;
							relationship.setPersistent(!relationship.isPersistent());
						}
					}
				}
			}
		});
	}

}
