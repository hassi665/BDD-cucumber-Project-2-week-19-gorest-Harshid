package com.gorest.restassured.cucumber.steps;

import com.gorest.restassured.gorestinfo.UsersSteps;
import com.gorest.restassured.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class MyStepdefs {

    static ValidatableResponse response;

    static String name = null;
    static String gender;
    static String email= null;
    static String status;
    static int userId;

    @Steps
    UsersSteps usersSteps;


    @When("^I create a new user by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status)  {
        name = TestUtils.getRandomValue()+_name;
        email = TestUtils.getRandomValue()+_email;
        response = usersSteps.createUser(name, email, gender, status).statusCode(201);
        userId = response.extract().path("id");


    }

    @Then("^I get user information by id$")
    public void iGetUserInformationById() {
            usersSteps.getUserById(userId);
    }


    @When("^Update user details by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void updateUserDetailsByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        name = name + "_updated";
        response = usersSteps.updateUser(userId, name, email, gender, status).statusCode(200);


    }

    @Then("^Verify user is updated$")
    public void verifyUserIsUpdated() {
        usersSteps.getUserById(userId);
    }

    @Then("^The user id deleted successfully$")
    public void theUserIdDeletedSuccessfully() {
        usersSteps.deleteUser(userId);
    }
}

