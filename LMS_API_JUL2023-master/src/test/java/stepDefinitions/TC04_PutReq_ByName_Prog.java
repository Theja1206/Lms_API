package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import dataFile.DataProvider;
import endpoint_methods.TC01_PostProgramMethod;
import endpoint_methods.TC03_PutProgramMethods;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.POJOPayload;
import resources.ConfigReader;
import resources.Constants;
import resources.Specification;

public class TC04_PutReq_ByName_Prog extends Specification
{
	
	public Scenario scenario;
	String request;
	@Before("@Put_ProgramName")
	public  void initializationTable(Scenario scenario) throws Exception
	{		this.scenario = scenario;
	        Constants.sheetPut = ConfigReader.getGlobalValue("sheetPutProgram");
	        Constants.excelReaderUtil.readSheet(Constants.sheetPut);
	}
	
	@Given("User updates program using PUT HTTPS Request with {string}")
	public void user_updates_program_using_put_https_request_with(String requestType) throws IOException
	{
		Constants.scenarioName = scenario.getName();
		 request = requestType;
	    
	    if (requestType.equalsIgnoreCase("Valid Program Name"))
	    	
	    {  	    	
	       if(Constants.respProg_Name == null)
		    {
	    	   if(DataProvider.getValue("respProg_Name") == null)
	    		   TC01_PostProgramMethod.createProgramID();
		    }
	    }
	    Constants.respProg_Name = (String) DataProvider.getValue("respProg_Name");
	    TC03_PutProgramMethods.putData(Constants.scenarioName);
	    
	    Constants.request = given().spec(requestSpecification()).body(POJOPayload.putRequestBody());
	    
	    ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Pass");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Request Body in POST Request : " + POJOPayload.putRequestBody());
	}

	

       @When("User calls {string} endpoint")
       public void user_calls_endpoint(String endpoint)
	{
    	 if (request.equalsIgnoreCase("invalid Program Name"))
		{
			response = Constants.request.when().put(resource(endpoint) + Constants.invalidProgName);
		} else
		{			
		response = Constants.request.when().put(resource(endpoint) + Constants.respProg_Name);
		}
	}

	@Then("User receives Status Code with updated program")
	public void user_receives_status_code_with_updated_program() throws IOException 
	{
		Constants.Prog_Name = getJsonPath(response, "programName");
		response.then().spec(responseSpecification(Constants.expectedStatus));
		
	}


}
