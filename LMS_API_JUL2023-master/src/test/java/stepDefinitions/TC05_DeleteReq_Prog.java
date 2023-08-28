package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

import dataFile.DataProvider;
import endpoint_methods.TC01_PostProgramMethod;
import endpoint_methods.TC04_DeleteProgramMethods;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.ConfigReader;
import resources.Constants;
import resources.Specification;
public class TC05_DeleteReq_Prog extends Specification{
	
	
	public static  Scenario scenario;
	public String sheetDelete;
	
	@Before("@DeleteProgram_byID")
	public  void initializationTable(Scenario scenario) throws Exception
	{		TC05_DeleteReq_Prog.scenario = scenario;
	        Constants.sheetDelete = ConfigReader.getGlobalValue("sheetDeleteProgram");
	        Constants.excelReaderUtil.readSheet(Constants.sheetDelete);
	}

	@Given("User Delete program using DELETE HTTPS Request")
	public static void user_delete_program_using_delete_https_request() throws IOException 
	{
		if(Constants.programId == null)
			
		{   Constants.scenarioName = scenario.getName();
		    TC01_PostProgramMethod.createProgramID();
	    }
		Constants.request = given().spec(requestSpecification());
	}
	

	@When("User calls {string} endpoint with {string}")
	public static void user_calls_endpoint_with(String endpoint, String programID) throws IOException 
	{
		
		response = Constants.request.when().delete(resource(endpoint) + Constants.programId);  
		Constants.programId = null;
	    DataProvider.setValue("programId",null);
	    Constants.respProg_Name = null;
	}
	
	@Then("User receives Status Code with success message")
	public static void user_receives_status_code_with_success_message() throws IOException 
	{
		Constants.scenarioName = scenario.getName();
		TC04_DeleteProgramMethods.deleteData(Constants.scenarioName);
		//Constants.request.expect().statusCode(Constants.expectedStatus).headers(headerValue()).body(containsString(Constants.successMessage));
		
		response.then().spec(responseSpecification(Constants.expectedStatus)).and().body(containsString(Constants.successMessage));
	}
	
	
}
