<div align="center">

# ClockedIn
**A Java based, surface level solution created for my Software Engineering course at WIT. Primarily utilizes the SOQL and REST API features from the Salesforce CRM platform to allow for OAuth sign in.**

[Watch a video demonstration of the software here](https://www.youtube.com/watch?v=CNlCZtdrzE4)

![screenshot](https://github.com/user-attachments/assets/2412abc5-98c5-405b-a7c2-d405f4dbb5cf)

</div>

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
