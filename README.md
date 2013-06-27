My-Medical-Hostory
==================
Content of this document:
1. Overview of the project
2. Required Android Permissions
3. Required Web Services, e.g., GIS, Google, etc., and why the app needs them
4. Testing procedures (emulators, device tests, unit tests, etc.)
5. Screenshots
6. Known bugs, limitations, and problems
7. Set of features to be implemented or next steps
8. License and contributors

Overview of this project:
My Medical History is an android native application. It allows the user to create and maintain family medical record history. It also allows the user to share this information with his physician or anybody else via email.

Required android permissions:
This android application requires no permissions.

Required web services:
This android application requires no access to web services.

Testing procedure:
There are two methods to test this app:
1. You can download “MyMedicalHistory.apk” available under bin folder. After downloading the file on your android device, you can proceed with installing the application.
2. Another way to test this app is to run it in any android emulator provided that it has a minimum SDK version of 16. You can point to emulator’s Internet browser to the bin folder and download “MyMedicalHistory.apk” file and install the application.

When you run the application for the first time, you will have an empty screen asking the user to start by adding people who are related to him. Clicking on the “Add Person” button will present the user with form to complete. Once the form is completed, a person will be added to the initial screen. Clicking on a person in the home screen will display the person’s details; the person’s details screen also allows the user to add or delete medical conditions for this person. There is a button in the home screen called “All Conditions”; clicking this button allows the user to list all medical conditions recorded for all people in this application. The same screen that displays the list of all medical conditions allows the user to share the same information with any person through email.
 

Known bugs/issues/limitations:
There are none.

Set of features to be implemented:
The application can be improved in several areas.
• Detecting any duplicate data when the user tries to add a new person with the same details.
• Validating that the entered dates are logical. An example of this would be not to allow the user to enter an acquiring date of a medical condition, which is earlier than the person’s birthdate.
• Allowing the user to rearrange the family tree as he desire.
• Allowing the user to display the family tree in other styles, for example displaying persons as a list.
• Allowing the user to get more information about a certain medical condition by clicking it. The application might take the user to a certain website with details information about this medical condition.

License and contributors:
Contributors to this project are Yusuf Abed (PhD candidate at Claremont Graduate University/ School of Information Systems and Technology) and Raeed Alkhalifa (MS Student at Claremont Graduate University/School of Information Systems and Technology email:raeed.alkhalifa@cgu.edu)
Any person is free to get this project’s code and modify it or redistribute it in anyways.
