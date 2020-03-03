# Industry Models - Glossary Tools

This set of tools contains functionality that supplements IBM Infosphere Governance Catalog (IGC), when Industry Models content is installed. Currently the following tools are available: 
* [Data class generation](#data-class-generation) - Generates Data Classes for compatible Industry Model content that has been installed. The Data Classes can then be used in conjunction with IBM Information Analyzer (IA), to classify data sources registered with Information Server. For example within a data lake environment, IA can use the generated data classes to auto-assign to Industry Models business terms (where possible) - more information is [here](#data-class-generation).
* [PDF term linker](#pdf-term-linker) - This capability will create hyperlinks within a PDF, to IGC business terms that are contained within  supplied parent category(s) - more information is [here](#pdf-term-linker).
### Install prerequisites
* Java 8.0 JRE/JDK or higher
* IBM Infosphere Information Governance Catalog (IGC) 11.7.0.1 or higher (note: IGC 11.7.0.0 is not supported due to REST api issue)
* Industry Models content installed on IGC
### Installation and execution
* Extract the contents of the [GlossaryTools-1.5.zip](GlossaryTools-1.5.zip) download
* Navigate to the /bin directory within the extracted download
* To execute on Windows
```
GlossaryTools.bat <toolname> <hostport> <username> <password>
<toolname> - This should be the name of the glossary tool. Valid values are: [dataclass, pdflink]
<hostport> - IBM IGC host information. Should include the prefix, e.g.: https://, and the port postfix :443/
<username> - IBM IGC login username. The user should have sufficient privileges for the functionality of the tool
<password> - IBM IGC login password for the supplied username
```
For example
```
GlossaryTools.bat dataclass https://igc.fyre.ibm.com:443/ user1 pass123
```
* To execute on Linux or Mac (not tested)
  * Make the script executable 
```
chmod +x GlossaryTools
```
```
GlossaryTools \<toolname\> \<hostport\> \<username\> \<password\>
```
Also see:
* [PDF term linker - running and additional parameters](#pdf-term-linker---running-and-additional-parameters)
* [Data class generation - additional parameters](#data-class-generation---additional-parameters)

# PDF term linker
This capability will create hyperlinks within a PDF, to IGC business terms that are contained within supplied parent category(s).

Background: Industry Models ships supportive content, that in some cases has been derived from a PDF of a standards or regulation. This supportive content will contain terms that are found in the original PDF and that are in turn assigned to core business terms in the glossary. The tool will create hyperlinks within a copy of the PDF pointing to the supportive content terms; this can be useful for visualizing the content within Industry Model supportive content, but in the context of the original pdf.

## PDF term linker - capabilities
* One or more parent category RIDs in IGC can be supplied to this tool, it will then query IGC for all the terms contained within those parent categories. The pdf will then be searched for each term name, and if found a hyperlink will be created to the IGC term.
* The tool will search for terms that span one line, but no more than one line, and also will not account for terms that span document pages.
* The tool will also check if a custom attribute exists in the IGC instance named 'similar document terms'; if it does, this will be queried for, and if a term has values set in the this custom attribute, they will also be searched for in the pdf and links created back to the orignial term. 
* All the terms that are returned from the IGC instance for the parent categories will be sorted in a list from longest to shortest. This sorted list of term names will then be searched for in the pdf from longest to shortest. For example let's say there were 3 terms, "Credit", "Rating" and "Credit Rating"; if the document contained a word "Credit Rating", then the term "Credit Rating" would get precedence when creating the corresponding hyperlink. 
* The colors of the term links and similar document term links can be set via the command line; defaults are grey and light grey, note both can be set to the same if don't want a distinction. 

## PDF term linker - running and additional parameters
In addition to the generic parameters outlined in [Installation and execution](#installation-and-execution), specific parameters need to be supplied in to run this pdf term linker. 

```
-help. To get this more information message.
Note: Any of the arguments below should be surrounded in quotes "" if they contain spaces
-inputPDF - (Mandatory) The PDF file and directory that will be read and not changed.
-outputPDF - (Mandatory) The PDF file and directory that will be created as a copy of the source PDF with hyperlinks to the glossary terms.
-categoryRID - (Mandatory) The RID(s) of the parent category that contains the terms in the glossary, that will be searched for in the PDF and linked. If specifying multiple parent catergory RID's please separate by semi-colon.
-linkColor - (Optional) The link color in hex to be used within the pdf document for the terms; the default is #808080 (Grey).
-linkColorSimilar - (Optional) The link color in hex to be used in the pdf document for the simlar terms; the default is #E0E0E0 (Light Grey). This can be set the same as the linkColor if do not want a distinction.
```
Format and order of arguments 
```
GlossaryTools.bat pdflink <hostport> <username> <password> -inputPDF=<pdf_name> -outputPDF=<pdf_name> -categoryRID=<rid>
```
For example
```
GlossaryTools.bat pdflink https://igc.fyre.ibm.com:443/ user1 pass123 -inputPDF=C:\temp\apis\GDPR.pdf -outputPDF=C:\temp\apis\GDPR-linked.pdf -categoryRID=1234qwerty1234
```
Another example - note semicolon list of parent category rids the hex link colors supplied, of red & blue. [Wiki Hex Colors](https://en.wikipedia.org/wiki/Web_colors#Hex_triplet)
```
GlossaryTools.bat pdflink https://igc.fyre.ibm.com:443/ user1 pass123 -inputPDF=C:\temp\apis\GDPR.pdf -outputPDF=C:\temp\apis\GDPR-linked.pdf -categoryRID=1234qwerty1234;4546qwerty4546 -linkColor=#FF0000 -linkColorSimilar=#0000FF
```

# Data class generation
When there is a need to auto-classify data source content, the data class generation capability becomes relevant. In Infosphere Governance Catalog (IGC) there is a type of Asset that categorises columnar data, called a [data class](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.bg.doc/topics/c_data_classification.html). There are a number of [data class types](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.bg.doc/topics/r_data_class_types.html), but for the purpose of the data class generation, the one that is relevant is data class of type [valid values](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.bg.doc/topics/r_data_class_valid_values.html). A data class with a list of valid values can be used by [Information Analyzer](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.ia.product.doc/topics/c_analysis.html), to classify the contents of a column automatically. This can result in an assigned data class and also assigned Industry Model business terms.

Industry Models can provide glossary content, that can be used as a source by the data class generator to create Data Classes. The generators describe the type of content expected in detail, but in summary there are two main types of glossary content:
* Terms that have a label 'enumeration' and a custom attribute that have valid values. See [Enumeration terms with custom attribute](#generator---enumeration-terms-with-custom-attribute) for more info
* Terms (labeled enumeration) that has type Terms labeled enumeration items. See [Enumeration terms with has types enumeration item terms](#generator---enumeration-terms-with-has-types-enumeration-item-terms) for more info

The generators will also attempt to set one or more assigned terms for the data class, assuming there is a recognisable navigation path from the term (from which the data class gets generated) to the business terms.

As part of the generation process, information will be added to the data class Note field, including:
  - URL of the source term from which the data class was created. This will usually be different than any assigned terms, as the source term is generally in supportive content, but the assigned terms are what the supportive term and now the data class, ultimately map too. 
  - Name and Path of the source term
  - Note: The above information may also be useful, in subsequent releases for lineage as to how the data classes were created, and in deciding upon upgrade approaches etc. This general pattern might also prove useful for any further generation endeavors

In addition, it is possible to markup new content in IGC, following the rules in the generator links below, to enable the Data Class generator to identify the terms and generate corresponding data classes for them:
* [Enumeration terms with custom attribute](#generator---enumeration-terms-with-custom-attribute)
* [Enumeration terms with has types enumeration item terms](#generator---enumeration-terms-with-has-types-enumeration-item-terms)

In summary, this glossary term content (that is used as input into the Data Class generator) has been marked up, to identify lists of valid values. If these valid values exist in columnar data, then the generated Data Classes could result in [auto classification](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.ia.product.doc/topics/c_term_assignment.html#c_term_overview) by Information Analyzer, to those generated data classes and by extension, any assigned Industry Models business terms of those data classes. 

## Data class generation - additional parameters
In addition to the generic parameters outlined in [Installation and execution](#installation-and-execution), specific optional parameters can be supplied for the data class generator. 

Note: There is no need to supply these parameters, if the default values outlined below are acceptable
```
-help. To get this more information message.
-batchmode:true or -batchmode:false. Default is false. Indicates if generator runs in batch, or not batch (user confirms each group of data classes to add). 
-maxitems:<integer>. Default is 2000. This indicates the maximum number of items that will be returned from glossary calls. If all expected data classes are not being created, this number might need to be increased. 
-valuedef:true or -valuedef:false. Default is true. If a definition exists on a value, this indicates whether it should be then appended to the long definition of the generated data class (as data class values themselves, cannot have definitions). 
-values:code or -values:name or -values:codeandname. Default is codeandname. Whether the data class list of values should be made up from code, names or codes and names. This could depend on: whether codes and names exist; how representative the codes and names, are of the data sources that ultimately need to be classified by the data classes. 
```
For example
```
GlossaryTools.bat dataclass https://igc.fyre.ibm.com:443/ user1 pass123 -values:codes
```
## Generator - Enumeration terms with custom attribute
This generator will attempt to generate a corresponding data class, for terms with the following characteristics
* Term has an 'enumeration' label
* Term has a custom attribute named 'Enumeration_Values', and the custom attribute contains either:
  * JSON array string with 'Code', 'Name' and 'Definition' fields (see example below)
  * List of Values (separated by newline)
```json
[{"Code": "Code111", "Name": "Code Name One", "Definition": "A definition for code name one"}, 
{"Code": "Code222", "Name": "Code Name Two", "Definition": "A definition for code name two"}]
```
* Supported navigation paths to assign business terms to the data class
  * Note: if there are no navigation paths to business terms, the data class will still be created but there will be no assigned terms. This may still be of value, as the name of the data class, is usually more expressive than the column that it is classifying and this expressive name can be taken into account by Information Analyzer when attempting to assign the column to a business term
  * The enumeration term (from which data class created) has related/assigned business terms (resides in 'Industry Models Business Vocabulary / Business Terms' category or sub category) - any related/assigned business terms will get assigned to the Data Class
  * The enumeration term (from which the data class created) is a supportive term (resides in 'Industry Models Business Vocabulary / Supportive Content' category or sub category) and has related supportive terms which have related/assigned business terms - these ultimate related/assigned business terms with get assigned to the Data Class. Note: usually applies only to Energy and Healthcare content.
  * The enumeration term (from which the data class created) is a supportive term and is a type of supportive terms which have related/assigned business terms - these ultimate related/assigned business terms will get assigned to the Data Class. Note: usually applies only to Banking and Insurance content.

## Generator - Enumeration terms with has types enumeration item terms
This generator will attempt to generate a corresponding data class, for terms with the following characteristics
* Term has an 'enumeration' label
* Term has type terms, that have an 'enumeration item' label
* The name of the enumeration item term will be prefixed by the enumeration term, separated by a dash
  * For example name of enumeration term = 'Gender', then valid values of the name of the has type enumeration items terms would be
    * Gender - Male
    * Gender - Female
* The definition of the enumeration item terms will flow into the data class long definition it they exist
* Supported navigation paths to assign business terms to the data class
  * Note: if there are no navigation paths to business terms, the data class will still be created but there will be no assigned terms. This may still be of value, as the name of the data class, is usually more expressive than the column that it is classifying and this expressive name can be taken into account by Information Analyzer when attempting to assign the column to a business term
  * The enumeration term (from which data class created) has a related business term (resides in 'Industry Models Business Vocabulary / Business Terms' category or sub category) - any related business terms will get assigned to the Data Class
  
## Tips when using the generated data classes with Information Analyzer
Below are some considerations when using Information Analyzer with the generated data classes.
* [Restricting Information Analyzer auto assignment](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.ia.product.doc/topics/r_term_assignment_matching_params.html) to only Industry Model business terms (not supportive and analytical requirements). Note: to get the rid of the Industry Models business term categories, select the context menu on a business term category and open in a new tab - the rid will now form part of the URL.
* Disabling all child data classes, by deselecting the generated parent in IGC. If a data source is going to be analyzed, and it is considered that a grouping (all share the same parent data class) is not relevant to that data source, it is possible to just disable the parent data class, and that applies to all child data classes too
* An alternate approach to the above, is within an Information Analyzer workspace, the data classes can be enabled or disabled too.

Related Links
* [Classify an asset according to its data](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.bg.doc/topics/t_data_classification.html)
* [Data classification](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.bg.doc/topics/c_data_classification.html)
* [Data class types](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.bg.doc/topics/r_data_class_types.html)
* [Analyzing data in Information Analyzer](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.ia.product.doc/topics/c_analysis.html)
* [Automatic Term Assignment in Information Analyzer](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_11.7.0/com.ibm.swg.im.iis.ia.product.doc/topics/c_term_assignment.html#c_term_overview)
* [Verifying data classes for accurate data classification](https://www.ibm.com/support/knowledgecenter/en/SSZJPZ_8.7.0/com.ibm.swg.im.iis.ia.profile.doc/topics/t_ca_review_data_class.html)
