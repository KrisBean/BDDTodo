package com.kris.todobdd.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.java8.En;

public class TodoUIStepDefinitions implements En {
	
Logger logger = LoggerFactory.getLogger(TodoUIStepDefinitions.class);

static WebDriver webDriver; 


//Initialize the chrome webdriver
private static WebDriver getWebDriver() {
	 if (webDriver == null) {
			System.setProperty("webdriver.chrome.driver", "C:/test/chromedriver.exe");
		    webDriver = new ChromeDriver();
		    webDriver.get("localhost:4200");
		    }
	 return webDriver;
}

public TodoUIStepDefinitions () {

	//Create a todo
	Given("^the user enters a todo item \"([^\"]*)\"$", (String title) -> {
		WebElement element = getWebDriver().findElement(By.id("title")); 
		element.sendKeys(title);
		element.sendKeys(Keys.ENTER);
	});

	Then("^the todo \"([^\"]*)\" is created and displayed on the page$", (String title) -> {
		WebElement todoListElement = getWebDriver().findElement(By.className("todo-list"));
		WebElement todoItem = todoListElement.findElement(By.xpath("//li[1]")).findElement(By.className("todo-title"));

		logger.debug("todo Title" + todoItem.getText());
		
		assertEquals(title, todoItem.getText());
		
	});

	//Update a todo task
	Given("^the user edits a todo item \"([^\"]*)\" with \"([^\"]*)\"$", (String oldTitle, String newTitle) -> {
		WebElement todoListElement = getWebDriver().findElement(By.className("todo-list"));
		WebElement todoItem = todoListElement.findElement(By.xpath("//li[1]"));
		todoItem.findElement(By.className("edit")).click();
		todoItem.findElement(By.className("todo-edit")).findElement(By.tagName("input")).clear();
		todoItem.findElement(By.className("todo-edit")).findElement(By.tagName("input")).sendKeys(newTitle);
		
	});
	
	When("^the user clicks Ok$", () -> {
		WebElement todoListElement = getWebDriver().findElement(By.className("todo-list"));
		WebElement todoItem = todoListElement.findElement(By.xpath("//li[1]"));
		todoItem.findElement(By.className("edit-actions")).findElement(By.linkText("done")).click();;
	});

	Then("^the todo title is updated to \"([^\"]*)\" and displayed on the page$", (String title) -> {
		WebElement todoListElement = getWebDriver().findElement(By.className("todo-list"));
		WebElement todoItem = todoListElement.findElement(By.xpath("//li[1]")).findElement(By.className("todo-title"));

		logger.debug("todo Title" + todoItem.getText());
		
		assertEquals(title, todoItem.getText());
	});

	//Delete a todo task
	Given("^the user deletes the \"([^\"]*)\" task$", (String title) -> {
		WebElement todoListElement = getWebDriver().findElement(By.className("todo-list"));
		WebElement todoItem = todoListElement.findElement(By.xpath("//li[1]"));
		assertEquals(title, todoItem.findElement(By.className("todo-title")).getText());
		todoItem.findElement(By.className("delete")).click();;
	});

	Then("^the task \"([^\"]*)\" is deleted from the page$", (String title) -> {
		WebElement todoListElement = getWebDriver().findElement(By.className("todo-list"));
		WebElement todoItem = todoListElement.findElement(By.xpath("//li[1]"));
		assertNotEquals(title, todoItem.findElement(By.className("todo-title")).getText());
	});
	

	//Close the browser
	Given("^the user closes the browser$", () -> {
		getWebDriver().close();
	});


}

}
