# contacts-rest-api
Partially implemented test task for Java Internship by CHI Software (test part is missing, some security issues are remaining, no validation for a phone number implemented). 

MySQL RDBMS was used. Create schema 'contacts' in MySQL Workbench before you start the app. Change username and password in application.properties file according to your local
settings.

Start the application in IDE (IntelliJ IDEA was used).

Test the application with applicable software (Postman was used).

NEW USER CREATION
POST localhost:8080/auth/register

USER LOGIN
POST localhost:8080/auth/register

GET ALL CONTACTS FOR PARTICULAR USER
GET localhost:8080/contacts

GET ALL DETAILS FOR PARTICULAR CONTACT
GET localhost:8080/contacts/{contactId}

CREATE NEW CONTACT
POST localhost:8080/contacts

DELETE CONTACT
DELETE localhost:8080/contacts/{contactId}

UPDATE CONTACT
PUT localhost:8080/contacts/{contactId}

GET EMAILS FOR PARTICULAR CONTACT
GET localhost:8080/contacts/{contactId}/emails

GET PHONES FOR PARTICULAR CONTACT
GET localhost:8080/contacts/{contactId}/phones

CREATE NEW EMAIL FOR PARTICULAR CONTACT
POST localhost:8080/contacts/{contactId}/emails

CREATE NEW PHONE FOR PARTICULAR CONTACT
POST localhost:8080/contacts/{contactId}/phones

UPDATE PARTICULAR EMAIL FOR PARTICULAR CONTACT
PUT localhost:8080/contacts/{contactId}/emails

UPDATE PARTICULAR PHONE FOR PARTICULAR CONTACT
PUT localhost:8080/contacts/{contactId}/phones

DELETE PARTICULAR EMAIL FOR PARTICULAR CONTACT
DELETE localhost:8080/contacts/{contactId}/emails/{emailId}

DELETE PARTICULAR PHONE FOR PARTICULAR CONTACT
DELETE localhost:8080/contacts/{contactId}/phones/{phoneId}

GET PARTICULAR EMAIL FOR PARTICULAR CONTACT
GET localhost:8080/contacts/{contactId}/emails/{emailId}

GET PARTICULAR PHONE FOR PARTICULAR CONTACT
GET localhost:8080/contacts/{contactId}/phones/{phoneId}
