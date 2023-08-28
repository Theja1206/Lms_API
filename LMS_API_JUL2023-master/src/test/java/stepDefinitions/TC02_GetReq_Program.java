package stepDefinitions;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import dataFile.DataProvider;
import endpoint_methods.TC02_GetprogramMethods;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.ConfigReader;
import resources.Constants;
import resources.Specification;

public class TC02_GetReq_Program extends Specification{

	public  Scenario scenario;
	public String sheetGet;
	public String program_ID;


	@Before("@GetProgram_Module")
	public void initializeDataTable(Scenario scenario) throws Exception {
		
		this.scenario = scenario;
		sheetGet = ConfigReader.getGlobalValue("sheetGetProgram");
		Constants.excelReaderUtil.readSheet(sheetGet);
		
	}

	
	@Given("User creates GET Request for LMS API endpoint")
	public void user_creates_get_request_for_lms_api_endpoint() throws IOException 
	{
		Constants.scenarioName = scenario.getName();
		Constants.request = given().spec(requestSpecification());

		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Given Step in TC02 GET Request Passed");
	}

	@When("User calls {string} Https Request for {string}")
	public void user_calls_https_request_for(String endpoint, String queryType) throws IOException 
	{
		Constants.queryType = queryType;
		
		if(queryType.equalsIgnoreCase("All Program")) 
		{
			response = Constants.request.when().get(resource(endpoint));
		}else if (queryType.equalsIgnoreCase("ProgramID"))
		{
			Constants.programId = (String) DataProvider.getValue("programId");
		
			if(Constants.programId == null) 
			{
				TC02_GetprogramMethods.getData(Constants.scenarioName);
				response = Constants.request.when().get(resource(endpoint) + Constants.programId);
				
		   }else 
		{
			  response = Constants.request.when().get(resource(endpoint) + Constants.programId);
				
		}		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "programID : " + Constants.programId);
		}else 
		{
			TC02_GetprogramMethods.getData(Constants.scenarioName);
			response = Constants.request.when().get(resource(endpoint) + Constants.invalidProgramId );
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "programID : " + Constants.invalidProgramId);
		}
				
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "When Step in TC02 GET Request Passed");
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Endpoint : " + endpoint);
		
	}
		
	@Then("User receive HTTP Status code and response body")
	public void user_receive_http_status_code_and_response_body() throws NumberFormatException, IOException 
	{
		
		if (Constants.queryType.equalsIgnoreCase("ProgramID"))
		{
		response.then().spec(responseSpecification(Constants.expectedStatus)).body(containsString(Constants.programId));
		}else if(Constants.queryType.equalsIgnoreCase("All Program")) 
		{
			TC02_GetprogramMethods.getData(Constants.scenarioName);
			response.then().spec(responseSpecification(Constants.expectedStatus));
		}
		
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Then Step in TC02 GET Request Passed");
	}


}
