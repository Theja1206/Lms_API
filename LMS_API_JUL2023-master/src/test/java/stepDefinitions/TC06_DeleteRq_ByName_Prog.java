package stepDefinitions;

import resources.Constants;
import resources.Specification;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.*;

public class TC06_DeleteRq_ByName_Prog extends Specification
{

	
	@Given("User Delete program HTTPS Request")
	public void user_delete_program_https_request() throws IOException 
	{
		Constants.request = given().spec(requestSpecification());
	}

	
	@When("User calls {string} endpoint by {string}")
	public void user_calls_endpoint_by(String endpoint, String programName) 
	{
		response = Constants.request.when().delete(resource(endpoint) + Constants.Prog_Name);
	}


	@Then("User receives Status Code <{int}> with message")
	public void user_receives_status_code_with_message(Integer statusCode) throws IOException 
	{
		response.then().spec(responseSpecification(statusCode));
	}
	
}
