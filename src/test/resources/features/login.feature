Feature: Login Feature

Scenario: Login with valid credentials 

Given user in on the login page
When user enters username "Admin" in the text box
And user enters password "admin123" in the text box
And user clicks on the login button
Then user should be navigated to the home page

Scenario: Login with invalid credentials
Given user in on the login page
When user enters username "Admina" in the text box
And user enters password "admian123" in the text box
And user clicks on the login button
Then user should see the error message
