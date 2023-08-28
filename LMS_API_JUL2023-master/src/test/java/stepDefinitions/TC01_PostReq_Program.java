package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import dataFile.DataProvider;
import endpoint_methods.TC01_PostProgramMethod;
import endpoint_methods.TC04_DeleteProgramMethods;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import pojo.POJOPayload;
import resources.ConfigReader;
import resources.Constants;
import resources.Specification;
import resources.TimeStamp;

public class TC01_PostReq_Program extends Specification

{

	public static String sheetPost;
	public static Scenario scenario;

	@Before("@PostProgram_Module")
	public void initializeDataTable(Scenario scenario) throws Exception {
		TC01_PostReq_Program.scenario = scenario;
		sheetPost = ConfigReader.getGlobalValue("sheetPostProgram");
		Constants.excelReaderUtil.readSheet(sheetPost);
	}

	@Given("User creates {string} Request with {string}")
	public static void user_creates_request_with(String method, String requestBody) throws IOException {

		Constants.methodName = method;
		Constants.requestBody = requestBody;
		
		if (Constants.scenarioName == null) 
		{
			TC01_PostProgramMethod.postData(scenario.getName());
		} else {
			
			TC01_PostProgramMethod.postData(Constants.scenarioName);
		}
		
		Constants.request = given().spec(requestSpecification()).body(POJOPayload.jsonUsingPOJO());

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"Request Body in POST Request : " + POJOPayload.jsonUsingPOJO());
	}

	@When("User calls {string} with {string} HTTP Request")
	public static void user_calls_with_http_request(String endpoints, String method) {
		Constants.endpoints = endpoints;
		response = Constants.request.when().post(resource(endpoints));

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "endpoints in POST Request : " + endpoints);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Method in POST Request : " + method);
	}

	@Then("User receives Status Code with response body and verify programId using {string} request method")
	public static void user_receives_status_code_with_response_body_and_verify_program_id_using_request_method(
			String endpoint) throws IOException {
		
		// ******** extracting data from response ***** //
		Constants.status = response.getStatusCode();
		Constants.date = response.getHeader("Date");
		Constants.headerDate = Constants.date.substring(0, Constants.date.length() - 7);
		Constants.programId = getJsonPath(response, "programId");
		Constants.respProg_Name = getJsonPath(response, "programName");

		DataProvider.setValue("programId", Constants.programId);
		Constants.programCreatedTime = getJsonPath(response, "creationTime");
		Constants.actualCreationTime = Constants.programCreatedTime.substring(0,
				Constants.programCreatedTime.length() - 10);

		// ********* expected Value *****//
		Constants.expectedCreationTime = TimeStamp.timeStamp();

		// *******Validation****//

		response.then().spec(responseSpecification(Constants.expectedStatus)).body(JsonSchemaValidator.
			      matchesJsonSchema(new File(ConfigReader.getGlobalValue("programSchema"))))
				.body(containsString(Constants.programName)).body(containsString(Constants.programStatus));

		assertEquals(Constants.headerDate, TimeStamp.headersTimeStamp());

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				" Program Name in POST Request : " + Constants.programName);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Program ID in POST Request : " + Constants.programId);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				"expected Status in POST Request : " + Constants.expectedStatus);

	}
	
	@Given("User creates {string} Request with programID in Request Body")
	public void user_creates_request_with_program_id_in_request_body(String requestBdy) throws IOException 
	{
		TC01_PostProgramMethod.postData(scenario.getName());
		Constants.request = given().spec(requestSpecification()).body(POJOPayload.requestBody_withId());
	}
	@Then("User receives failed Status Code")
	public void user_receives_failed_status_code()throws IOException 
	{
		int status_Code = response.getStatusCode();
		TC04_DeleteProgramMethods.deletemethods();
	    Assert.assertEquals(status_Code, Constants.expectedStatus);  
	}
	
	

}
