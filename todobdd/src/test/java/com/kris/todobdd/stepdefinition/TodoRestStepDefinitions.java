package com.kris.todobdd.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

public class TodoRestStepDefinitions implements En {
	
	private RequestSpecification request; 
	private Response response; 
	
	Logger logger = LoggerFactory.getLogger(TodoRestStepDefinitions.class);
	
	public TodoRestStepDefinitions() {
	
	//Background step to initialize the request  
	Given("^I initialize the host \"([^\"]*)\" and port \"([^\"]*)\" and path \"([^\"]*)\"$", (String host, String port, String path) -> {
		RestAssured.urlEncodingEnabled = true;
		RestAssured.baseURI = host;
		RestAssured.port = Integer.parseInt(port);
		RestAssured.basePath = path;
		request = RestAssured.with().request(); 
	});

	//Post 
	When("^I Post a todo \"([^\"]*)\"$", (String content) -> {
		System.out.println("URL is " + RestAssured.baseURI);
		String jsonContent = "{\"title\":\"" + content + "\"}";
		System.out.println("Content is : " + jsonContent);
		
		response = request.contentType(ContentType.JSON).body(jsonContent).post("");
		assertNotNull(response);
	});

	//Get All 
	When("^I make a GET request$", () -> {
	    response = request.get();
	});

	Then("^I receive the list of Todos$", () -> {
		logger.debug("GET ALL Response: " + response.prettyPrint());
		assertNotNull(response);
	});

	//Get One 
	When("^I make a GET request to retrieve the todo task with \"([^\"]*)\"$", (String title) -> {
		response = request.get("/"+title);
		logger.debug("GET ONE Response : " + response.prettyPrint());
	});

	//Put
	When("^I update the \"([^\"]*)\" title with \"([^\"]*)\" title$", (String exttitle, String newtitle) -> {
		
		String id = request.get("/"+exttitle).jsonPath().getString("id");
		response = request.contentType(ContentType.TEXT).body(id).put("/"+newtitle);
	});

	Then("^I verify the if \"([^\"]*)\" title is present in the response$", (String title) -> {
		logger.debug("Updated response with new title: " + request.get("/" + title).prettyPrint());
		assertEquals("Test BDD", request.get("/"+title).jsonPath().getString("title"));
	}); 

	//Delete 
	When("^I Delete a task with \"([^\"]*)\"$", (String title) -> {
	   response = request.delete("/"+title);
	});
	
	// Common step to Verify the response status is 200 
	Then("^I verify if the response status is (\\d+)$", (Integer statusCode) -> {
		assertEquals(statusCode.intValue(), response.getStatusCode());
	});
	
	}	

}
