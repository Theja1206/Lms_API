package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import dataFile.DataProvider;
import endpoint_methods.TC01_PostProgramMethod;
import endpoint_methods.TC03_PutProgramMethods;
import endpoint_methods.TC04_DeleteProgramMethods;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.POJOPayload;
import resources.ConfigReader;
import resources.Constants;
import resources.Specification;
import resources.TimeStamp;

public class TC03_PutReq_ById_Prog extends Specification
{
	
	public  Scenario scenario;
	public String id_Type;
	
	@Before("@PutProgram_Module")
	public  void initializationTable(Scenario scenario) throws Exception
	{		this.scenario = scenario;
	        Constants.sheetPut = ConfigReader.getGlobalValue("sheetPutProgram");
	        Constants.excelReaderUtil.readSheet(Constants.sheetPut);
	}
	
	@Given("User updates Put Request with {string}")
	public void user_updates_put_request_with(String requestType) throws IOException 
	{
		Constants.scenarioName = scenario.getName();
		
	    if (requestType.equalsIgnoreCase("valid request body"))
	    	
	    {  Constants.programId = (String) DataProvider.getValue("programId");
	    	
	       if(Constants.programId == null)
		    {
		    	TC01_PostProgramMethod.createProgramID();
		    }
	    }
	    TC03_PutProgramMethods.putData(Constants.scenarioName);
	    
	    Constants.request = given().spec(requestSpecification()).body(POJOPayload.putRequestBody());
	    
	    ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request Body in POST Request : " + POJOPayload.putRequestBody());
	}


	@When("User calls {string} with {string} HTTP Request {string}")
	public void user_calls_with_http_request(String endpoint, String method, String ProgramID) 
	{
		id_Type = ProgramID;
		if(ProgramID.equalsIgnoreCase("Valid ProgramID")) 
		{
			response = Constants.request.when().put(resource(endpoint) + Constants.programId);
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "When Step in TC03 in PUT Request ");
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Printing programID  from API LMS Excel : " + Constants.programId);
		}
		else
		{
			response = Constants.request.when().put(resource(endpoint) + Constants.invalidProgramID);
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "When Step in TC03 in PUT Request ");
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Printing programID  from API LMS Excel : " + Constants.invalidProgramID);
		}
	
	}

	// Positive scenario validation in Put Program 
	@Then("User receives Status Code with updated program Name")
	public void user_receives_status_code_with_updated_program_name() throws IOException 
	{
		
		Constants.expectedCreationTime = TimeStamp.timeStamp();
		Constants.respProg_Name = getJsonPath(response, "programName");
		DataProvider.setValue("respProg_Name",Constants.respProg_Name);

		response.then().spec(responseSpecification(Constants.expectedStatus)).body(containsString(Constants.updateprogramName)).
		                     body(containsString(Constants.updateprogramStatus));
		
				ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
				ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Printing Status Code from API LMS Excel :  " + Constants.expectedStatus);
				ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Printing expectedCreationTime :  " + Constants.expectedCreationTime);
	}
		// Negative scenario validation in Put Program 
	
	@Then("User receive HTTP Status code and error message")
	public void user_receive_http_status_code_and_error_message() throws IOException 
	
	{
		    int status_Code = response.getStatusCode();
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Response Body Log : " + response.print());
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Printing Error Message :  " + Constants.errorMessage);
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Log Status Code :  " + status_Code );
					
			//Constants.request.expect().statusCode(Constants.expectedStatus).headers(headerValue());
			
			if(status_Code == 200)
			{			
				TC04_DeleteProgramMethods.deletemethods();
			     Constants.programId = null;
			     DataProvider.setValue("programId",null);
			     
			     response.then().spec(responseSpecification(Constants.expectedStatus));
			}else
			{
				TC04_DeleteProgramMethods.deletemethods();
			    Assert.assertEquals(status_Code, Constants.expectedStatus);
			}
			
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Log Expected Status Code :  " + Constants.expectedStatus );
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Log Expected Status Code :  " + headerValue() );
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
	
	}
	
	// Negative scenario validation in Put Program 
	@Then("User receive HTTP Status code with error message")
	public void user_receive_http_status_code_with_error_message() throws IOException
    {
		Constants.actualErrorMessage = getJsonPath(response, "message");
		Constants.responseSuccessState = getJsonPath(response, "success");
		
		response.then().spec(responseSpecification(Constants.expectedStatus)).
		                body(containsString(Constants.errorMessage)).
		                body(containsString(Constants.successState));
        }
	//Put Request for the existing Program name 
	
	@Given("User updates Put Request with {string} and {string}")
	public void user_updates_put_request_with_and(String requestType, String existingValue) throws IOException 
	{
		Constants.scenarioName = scenario.getName();
		TC01_PostProgramMethod.createProgramID();
        TC03_PutProgramMethods.putData(Constants.scenarioName);
	    
	    Constants.request = given().spec(requestSpecification()).body(POJOPayload.putRequestBody());
	    
	    ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request Body in POST Request : " + POJOPayload.putRequestBody());
	}
	
	@When("User calls {string} with {string} HTTP Request after creating {string}")
	public void user_calls_with_http_request_after_creating(String endpoint, String method, String ProgramID) 
	{
		response = Constants.request.when().put(resource(endpoint) + Constants.programId);
		
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "When Step in TC03 in PUT Request ");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Printing programID  from API LMS Excel : " + Constants.programId);
	}

}
