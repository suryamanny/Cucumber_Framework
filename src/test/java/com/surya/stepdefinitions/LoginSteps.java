package com.surya.stepdefinitions;

import com.surya.hooks.Hooks;
import com.surya.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.*;

public class LoginSteps {

	private static final Logger logger = LogManager.getLogger(LoginSteps.class);

	LoginPage loginPage = new LoginPage(Hooks.driver);

	@Given("user in on the login page")
	public void user_in_on_the_login_page() {
		logger.info("User is on the login page");
	}

	@When("user enters username {string} in the text box")
	public void user_enters_username_in_the_text_box(String username) {
		logger.info("Entering username: " + username);
		loginPage.enterUsername(username);
	}

	@When("user enters password {string} in the text box")
	public void user_enters_password_in_the_text_box(String password) {
		logger.info("Entering password");
		loginPage.enterPassword(password);
	}

	@When("user clicks on the login button")
	public void user_clicks_on_the_login_button() {
		logger.info("Clicking on the login button");
		loginPage.clickLoginButton();
	}

	@Then("user should be navigated to the home page")
	public void user_should_be_navigated_to_the_home_page() {
		logger.info("Home page validated successfully");
	}

}
