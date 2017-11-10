
Note: the items in this project are being shared on an "as-is" basis. Please review the 'as-is' instruction at the root of this repository for details - covering the use of these item(s); https://github.com/IBM-IndustryModels/Tooling-As-Is

# add-urls-pdf

Marks up a specified PDF with hyperlinks for terms from two types of sources:
1. The terms contained within an IBM Infosphere Governance Catalog (IGC) category
2. or CSV file (see format below)

Features:
- Will create hyperlinks in a new pdf file, from a list of terms (words) & links, that are read from IGC or CSV
- Can apply different colored links for terms with one label defined
- Can apply multi colored links for terms that have many labels defined
- Note: the colors for the labels can be specified in the config file 

Environment:
- The batch file will only work on windows, but could be used as a template to create a shell script for working on other environments (other environments not tested, but should work on any environment that supports Java 1.7)

Usage:
- Java JRE 1.7 installed (or higher, only tested with 1.7 and 1.8 tested)
- Download the following files to a working directory: AddURLsToPDF.bat, AddURLsToPDF.jar, config.properties
- Edit the input_pdf & output_pdf properties in the batch file 
- The colors to be used for labels can be specified in the config file. If multi labels on a term, the hyperlink is multi-colored. The defaults for the colors are (gdpr_process = red, gdpr_data = black, gdpr_other = blue), The default is black (if no color specified for a label, or no label).
- A term will be ignored for pdf markup if they have a specified prefix, this is customisable in the config file, default is IGNORE:
- Technical: main entry class: com.ibm.im.AddURLsToPDF

Usage - sepcific next steps if input is IGC
- Edit the IGC properties in the batch file igc_category_rid, igc_host_port, igc_username & igc_password
- Confirm that the line in the batch file below 'use this command for calling via IGC' is uncommented 
- Run the batch file
- Note: the igc_category_rid, is the unique identifier of the parent category in IGC that contains the terms that want to create hyperlinks for in a pdf. Only one rid (parent category) can be specified. To determine the rid for a category, open the category in a new browser tab and the rid starts after the part of the url 'dossierView/' and ends before the '?'
- Note the format of the call is: IGC <input-pdf> <output-linked-pdf> <igc_category_rid> <igc_hostport> <igc_username> <igc_password>

Usage - specific next steps if input is CSV
- Edit the CSV properties in the batch file csv_file 
- Confirm that the line in the batch file below 'use this command for calling via CSV' is uncommented 
- Run the batch file
- Note the format of the call is: : CSV <input-pdf> <output-linked-pdf> <links-csv>
- Note the format of the <links-csv> is: [PDF Text to link], [URL Text], [URL], [labels]
- Note the format of the [labels] is the labels delimiter of a space

This uses: 
- Apache PDFBox 2.0.4  (for manipulating PDFs)
- Apache Wink 1.4  (for REST calls & interpreting response)
- Apache HTTP Client 4.5.3  (for handling server connection)

Known Issues
- If a label is found on a term and there is no reference to it in the config file an error will be reported. It would be better that this was handled quietly, and default color for labels used 
- If it can't successfully connect to the IGC server, a stack trace happens, instead of failing more gracefully

Future Enhancements (not a commitment)
- Allow multiple IGC parent categories to be specified
