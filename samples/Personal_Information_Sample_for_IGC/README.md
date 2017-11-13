### Notices
© Copyright IBM Corp. 2017. All Rights Reserved.
Clients are responsible for ensuring their own compliance with various laws and regulations, including the European Union General Data Protection Regulation. Clients are solely responsibility for obtaining advice of competent legal counsel as to the identification and interpretation of any relevant laws and regulations that may affect the clients’ business and any actions the clients may need to take to comply with such laws and regulations. The products, services, and other capabilities described herein are not suitable for all client situations and may have restricted availability. IBM does not provide legal, accounting or auditing advice or represent or warrant that its services or products will ensure that clients are in compliance with any law or regulation.

Note: the items in this project are being shared on an "as-is" basis. Please review the 'as-is' instruction at the root of this repository for details - covering the use of these item(s); https://github.com/IBM-IndustryModels/Tooling-As-Is‪

# Industry Models Personal Information Sample
The full Industry Models have robust, rich feature offerings available in a number of Industries:
- IBM Banking and Financial Markets Data Warehouse:
  - https://www.ibm.com/us-en/marketplace/banking-and-financial-markets-data-w
- IBM Insurance Information Warehouse: 
  - https://www.ibm.com/us-en/marketplace/insurance-information-warehouse
- IBM Unified Data Model for Healthcare: 
  - https://www.ibm.com/us-en/marketplace/unified-data-model-for-healthcare
- IBM Data Model for Energy and Utilities: 
  - https://www.ibm.com/us-en/marketplace/data-model-for-energy-and-utilities

In order to provide a indication, as to the type of content available in a full Industry Model offering, a sample has been provided that includes the domains of Person, Organisation and Customer and the Personal Information that might be contained therein.

## Pre-requisites
IBM Information Governance Catalog (IGC) 11.5.0.2 installed

## Installing the Sample
- Download the industry-models-personal-information-sample.xml from this repository
- Import into IGC using Administration > Home > Import > XML

## Overview of the Sample
The sample has main terms like Person and Organization. In addition these main terms have property terms like 'full name' and 'date of birth'. The main terms can also be related to one another, like Customer of a particular Organization. Also property terms may be in one of more inheritance hierarchies to indicate whether the information conveyed by the property could be personally identifying, sensitive or related to health.

### Main Terms and their relationships to other main terms
Person and Organization extend Party, and a Party can play the role of a Customer. Customer can be involved in a Customer Transaction where a Credit Card may be used. A Person can have a Home Address specified and in addition a Person may be related to many Assets such as Networked Device, Property and Vehicle.
### Property Terms
There are a number of property terms that describe the main terms, for example Vehicle has a 'vehicle registration number' and Person has a 'national identifier'. 
In addition there are a number of inheritance hierarchies for the property terms and three of these indicate the possible personal nature of the property terms
- Personal Identifying Information such as 'photograph' and 'last name'
- Personal Sensitive Information such as 'religion' 
- Personal Health Information such as 'blood type'

## Using the Sample 
The sample terms can be used within the IGC environment in order to map Information Assets that have been imported into IGC against. Information Assets that can be imported into IGC included Data Sources and Logical Models. The columns in a Data Source could be mapped to the terms in the sample, thereby documenting the type of information contained in the database column, for example full name, and by extension that that could be considered identifying information.

## Extending the Sample
Before extending the sample in a major piece of work, consider whether a full Industry Model might be an option; more information can be found by visiting the links at the top of the page.
### Guidelines when extending
- Consider if the term being added is a main term or a property term
- Decide which parent category to add the term too. The parent category can be thought of as a namespace or container of the term. If it does not neatly fit into one of the existing parent categories, then create a new one
- Give the term a definition 
- In the case of a main term, consider whether it is inheriting from another main term. Also consider whether it is related to any other main terms
- In the case of a property term, consider whether this term is extending another property term. Also in this step consider whether this term might be personally identifying, sensitive or related to health, and if so it should inherit ultimately from the respective term

## Upgrading from the Sample to the Full Industry Models
In order to upgrade from the sample to the Full Industry Model content, can follow the steps outlined here https://www.ibm.com/support/knowledgecenter/en/SSN364_8.9.1/com.ibm.ima.tut/tut/pi_sample/pi_sample_upgrade.html

