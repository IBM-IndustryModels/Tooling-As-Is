# Industry Models Additional Utilities

Note: the items in this project are being shared on an "as-is" basis. 
Please review the 'as-is' instruction at the root of this repository for details - covering the use of these item(s);
https://github.com/IBM-IndustryModels/Tooling-As-Is

Notes on this release - version 4.1.3. The main purpose of this refresh is to make available a compatible version of additional utilities with the Industry Models Utilities 4.1.3 (available on fix Central. See https://www.ibm.com/support/pages/node/3608685 for more details). The Swagger / RADM to Swagger Generation for RSA users has been removed in this latest version of Industry Models Additional Utilities 4.1.3. The utility was to carry users over until RSA built out Swagger support - which is now the case. This means that there is no longer additional utilities specifically for RSA users. The additional utilities cover IDA and/or IGC for Eclipse users.

From time to time, the IBM Industry Models team  make available 'Industry Models - Additional Utilities'. If this folder does not contain any repository installers (.zip files) then there are no current additional utilities available. 

When using both the 'Industry Models Utilities' and 'Industry Models Additional Utilities' -- it is imperative that compatible builds
are only installed together. This is because 'Industry Models Additional Utilities' has a hard dependency on being installed after
a compatible 'Industry Models Utilities'. e.g. The version within of installation repositories e.g. 4.1.3.20200122-1500 ... identifies compatible installation pairs.

In order to install the 'Industry Models Additional Utilities' - please ensure you have an uptodate 'Industry Models Utilities' installed. Install or update your 'Industry Models Utilities' following your knowledge center instructions (links included below - see chapter "Installing Industry Models Utilities"). 

To check what version of 'Industry Models Utilities' you have installed in your eclipse application (e.g. IDA and/or RSA) - see Help, About, Installation Details. If you have installed the Industry Models Utilities from an earlier version of our Banking or Insurance offerings - then update to the latest version (remove old plugins and install new) - before installing the 'Industry Models Additional Utilities' outlined in this instruction.

# To install the Industry Models Additional Utilities:
If you want to use IGC with IDA or RSA, install the IGC for Eclipse plug-in before you install the IBM Industry Model Utilities & Industry Models Additional Utilities.
* In IDA or RSA, go to the Help menu and click Install New Software.
* On the Available Software wizard page, click Add.
* In the Add Repository window, click Archive.
* In the Repository archive window, select the Industry Models Additional Utilities update site from the installation directory:
       https://github.com/IBM-IndustryModels/Tooling-As-Is/blob/master/Industry-Models-Additional-Utilities/com.ibm.ima.ste.utilities.additional_4.1.3.20200122-1500.jar
* In the Add Repository window, click OK.
* On the Available Software wizard page, select Group items by category.
* From the displayed list, select the category that best matches your environment and click Next. For more information, see 'Categories Explained' below.
    On the Install Details wizard page, click Next.
    On the Review Licenses wizard page (if displayed), read the license text. If you choose to accept the license agreement click I accept the terms of the license agreement and click Finish. Otherwise, click Cancel to cancel the installation.
    If a security warning dialog displays, click OK. When installation is complete, a message displays to inform you that you need to restart IDA for the changes to take effect.
* After you restart IDA, you can verify that the Industry Models Utilities features were installed. Go to the Help menu and select About InfoSphere Data Architect. Click Installation Details. IBM Industry Models Utilities features are listed on the Installed Software tab.

# Addendum: Categories explained
The following explains the categories options for installing Industry Models Utilities / Industry Models Additional Utilities
(choose your product Knowledge Center)

## IBM Banking and Financial Markets Data Warehouse
https://www.ibm.com/support/knowledgecenter/SSN364/welcome_family.html

## IBM Insurance Information Warehouse
https://www.ibm.com/support/knowledgecenter/SSRAR8/welcome_family.html

## IBM Banking Process and Service Models
https://www.ibm.com/support/knowledgecenter/SSRA3Z/welcome_family.html

## Note: 
* There is no direct support offered for 'Industry Models Additional Utilities' or other 'Tooling-as-is'components. 
* 'Industry Models Utilities' issues or problems can be raised by opening a case against your product using;
https://www.ibm.com/mysupport/s/?language=en_US

Regards,
Industry Models Development Team, IBM
