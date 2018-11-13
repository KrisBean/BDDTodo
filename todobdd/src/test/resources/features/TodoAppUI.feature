@todoui
Feature: As a user, I want to launch the todo app and create a todo list
 
Scenario:
 Given the user enters a todo item "BDD Test"
 Then the todo "BDD Test" is created and displayed on the page

Scenario:
 Given the user edits a todo item "BDD Test" with "Test BDD"
 When the user clicks Ok
 Then the todo title is updated to "Test BDD" and displayed on the page

Scenario:
 Given the user deletes the "Test BDD" task
 Then the task "Test BDD" is deleted from the page

Scenario: 
Given the user closes the browser