# Industry Models Additional Utilities

Note: The items in this project are being shared on an "as-is" basis. 
Please review the 'as-is' instructions at the root of this repository for details:
https://github.com/IBM-IndustryModels/Tooling-As-Is

From time to time, the IBM Industry Models team make available 'Industry Models - Additional Utilities'. If this folder does not contain any repository installers (.zip files) then there are no current additional utilities available. 

When using both the 'Industry Models Utilities' and 'Industry Models Additional Utilities', it is imperative that compatible builds
are only installed together. 'Industry Models Additional Utilities' has a hard dependency on being installed after
a compatible 'Industry Models Utilities'. e.g. The version within of installation repositories e.g. 04110.I20170726-1705 ... identifies compatible installation pairs.

In order to install the 'Industry Models Additional Utilities' - please ensure you have an uptodate 'Industry Models Utilities' installed. Install or update your 'Industry Models Utilities' using;
http://www.ibm.com/support/fixcentral/quickorder?product=ibm%2FInformation+Management%2FIBM+Banking+and+Financial+Markets+Data+Warehouse&fixids=industry-models-utilities.04110.I20170726-1705.runtime-update_site.zip&source=SAR

To check what version of 'Industry Models Utilities' you currently have installed in your eclipse application (e.g. IDA and/or RSA), please go to Help -> About -> Installation Details. If you have installed the Industry Models Utilties from an 8.9.1 or an earlier version of our Banking or Insurance offerings, please use the link above to update to the latest version prior to installing the 'Industry Models Additional Utilities'.

# To install the Industry Models Additional Utilities:
If you want to use IGC with IDA or RSA, install the IGC for Eclipse plug-in before you install the IBM Industry Model Utilities & Industry Models Additional Utilities.
* In IDA or RSA, go to the Help menu and click Install New Software.
* On the Available Software wizard page, click Add.
* In the Add Repository window, click Archive.
* In the Repository archive window, select the Industry Models Additional Utilities update site from the installation directory:
https://github.com/IBM-IndustryModels/Tooling-As-Is/blob/master/Industry-Models-Additional-Utilities/com.ibm.ima.ste.utilities.additional_4.1.1.20170726-1327.jar
* In the Add Repository window, click OK.
* On the Available Software wizard page, select Group items by category.
* From the displayed list, select the category that best matches your environment and click Next. For more information, see 'Categories Explained' below.
    On the Install Details wizard page, click Next.
    On the Review Licenses wizard page (if displayed), read the license text. If you choose to accept the license agreement click I accept the terms of the license agreement and click Finish. Otherwise, click Cancel to cancel the installation.
    If a security warning dialog displays, click OK. When installation is complete, a message displays to inform you that you need to restart IDA for the changes to take effect.
* After you restart IDA, you can verify that the Industry Models Utilities features were installed. Go to the Help menu and select About InfoSphere Data Architect. Click Installation Details. IBM Industry Models Utilities features are listed on the Installed Software tab.

# Note: Categories explained
The following explains the categories options for installing Industry Models Utilities / Industry Models Additional Utilities
(choose your relevant product Knowledge Center)

## IBM Banking and Financial Markets Data Warehouse
https://www.ibm.com/support/knowledgecenter/SSN364_8.9.1/com.ibm.ima.adm/soa/InfoSphere/too_gui/im_uti_cat.html

## IBM Insurance Information Warehouse
https://www.ibm.com/support/knowledgecenter/SSRAR8_8.9.1/com.ibm.ima.adm/soa/InfoSphere/too_gui/im_uti_cat.html

## IBM Banking Process and Service Models
https://www.ibm.com/support/knowledgecenter/SSRA3Z_8.9.1/com.ibm.ima.too_gui/soa/InfoSphere/too_gui/im_uti_cat.html

## Note: 
* There is no direct support offered for 'Industry Models Additional Utilities' or other 'Tooling-as-is'components. 
* 'Industry Models Utilities' issues or problems can be raised by opening a case against your product using the IBM Support website:
https://www.ibm.com/mysupport/s/topic/0TO500000002Cu5GAE/industry-models?productId=01t50000004XO7L

Regards,
Industry Models Development Team, IBM
