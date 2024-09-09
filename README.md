CLOCKEDIN: A surface level solution created for my Software Engineering course at WIT. Primarily utilizes the SOQL and REST API features from the Salesforce CRM platform to allow for OAuth sign in. 

Features:

- Easy and seamless sign in via OAuth 2.0, offered by Salesforce
- Option to punch in/punch out according to system time
- Ability to send "messages" that are forwarded to anyone with administrative access in the Salesforce org
- Easy access to a contingent employee schedule that can be changed by both employees and org administrators
- Seamless integration to the connected Salesforce org allows for direct and immediate object manipulation on the administrative end

Future Updates:

- The ability to change a schedule from the employee end currently lacks an approval process, allowing for non-admins to manipulate their schedule as they please
- Employee schedule is only extended for a 7 day period; more days or perhaps a customizable time format would be beneficial.
- Changes are not made in real time with scheduling; schedule day objects are only updated at the time of authentication via OAuth2, which can be changed by making updates based on a system timer or JavaFX scene change condition.

*Functionality for this software is currently only working for my own self generated Salesforce org. I may create a download link later with an attached .txt file that can be updated to allow for details of users' Salesforce orgs to be inputted.

- I will eventually add a link here for my project presentation which goes over the general functionality of the software and the reason for its creation. A downloadable .msi installer or .zip file is currently not available as I, as mentioned above,
have not implemented the functionality to allow for ANY user except myself to access the org/server.
