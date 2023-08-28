package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import batchPayload.BatchPayload;
import endpoint_methods.TC01_PostProgramMethod;
import endpoint_methods.TC07_PostBatchMethods;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.ConfigReader;
import resources.Constants;
import resources.Specification;

public class TC07_Post_req_Batch  extends Specification{
	
	
	public static Scenario scenario;
	String postBatch;
	 
	@Before("@Post_Batch")
	public void initializeDataTable(Scenario scenario) throws Exception {
		TC07_Post_req_Batch.scenario = scenario;
		postBatch = ConfigReader.getGlobalValue("sheetPostBatch");
		Constants.excelReaderUtil.readSheet(postBatch);
	}
	
	@Given("User creates batch using post request with {string}")
	public void user_creates_batch_using_post_request_with(String reqType) throws IOException 
	{
		Constants.scenarioName = scenario.getName();
		if(Constants.programId == null) 
		{
			TC01_PostProgramMethod.createProgramID();
		}
		
		TC07_PostBatchMethods.postBatchData(Constants.scenarioName);
		Constants.request = given().spec(requestSpecification()).body(BatchPayload.postBatch());
	}

	@When("User calls {string}")
	public void user_calls(String endpoint) 
	{
		response = Constants.request.when().post(resource(endpoint));

	}
	@Then("User receives Status Code with response body")
	public void user_receives_status_code_with_response_body() throws IOException 
	{
		TC07_PostBatchMethods.statusCode(Constants.scenarioName);
		Constants.batchId = getJsonPath(response, "batchId");
		response.then().spec(responseSpecification(Constants.expectedStatus));
	}

	

}
