@todorest
Feature: Todo Rest Operations
  Background:
    Given I initialize the host "http://localhost" and port "8080" and path "/api/todos"
    
@todo-post
Scenario Outline: I Post a todo task
 When I Post a todo "<task>"
 Then I verify if the response status is 200
 
 Examples:
 |task|
 |BDD Test|
 
@todo-getall
Scenario: Get the list of all todo tasks
 When I make a GET request
 Then I verify if the response status is 200
 And I receive the list of Todos 
 
@todo-getone
Scenario Outline: Get the requested todo task
 When I make a GET request to retrieve the todo task with "<title>"
 Then I verify if the response status is 200
 
 Examples:
 |title| 
 |BDD Test| 
 
@todo-put
Scenario Outline: Put request to Edit a todo item
 When I update the "<existing>" title with "<new>" title
 Then I verify if the response status is 200
 And I verify the if "<new>" title is present in the response
 
 Examples:
 |existing|new|
 |BDD Test|Test BDD|

@todo-delete
Scenario Outline: Delete a todo task
 When I Delete a task with "<title>"
 Then I verify if the response status is 200
 
 Examples: 
 |title|
 |Test BDD| 