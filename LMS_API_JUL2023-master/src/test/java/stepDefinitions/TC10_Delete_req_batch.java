package stepDefinitions;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import endpoint_methods.TC04_DeleteProgramMethods;
import io.cucumber.java.en.*;
import resources.Constants;
import resources.Specification;

public class TC10_Delete_req_batch extends Specification {
	
	

	@Given("User Delete batch using DELETE HTTPS Request")
	public void user_delete_batch_using_delete_https_request() throws IOException 
	{
		Constants.request = given().spec(requestSpecification());
	}

	@When("User calls {string} endpoint with valid batch Id")
	public void user_calls_endpoint_with_valid_batch_id(String endpoint) {
		
		response = Constants.request.when().delete(resource(endpoint)+Constants.batchId);
	}

	@Then("User receives Status Code <{int}>with success message in response")
	public void user_receives_status_code_with_success_message_in_response(Integer statusCode) throws IOException 
	{
		TC04_DeleteProgramMethods.deletemethods();
		response.then().spec(responseSpecification(statusCode));
		Constants.batchId = null;
		Constants.programId = null;
	}

	
}
