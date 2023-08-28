package endpoint_methods;

import java.io.IOException;

import resources.ConfigReader;
import resources.Constants;
import stepDefinitions.TC01_PostReq_Program;

public class TC01_PostProgramMethod 
{

	 public static void postData(String scenarioName) throws IOException 
	 {
		 // ********* read data from excel and store it in hash map ****//
	   		
		Constants.programName = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Program Name");
		Constants.programDesc = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Program Desc");
		Constants.programStatus = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Program Status");
		Constants.expectedStatus = Integer.parseInt(Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Status Code"));
		
	 }
		public static void createProgramID() throws IOException
		
		{
			TC01_PostReq_Program.user_creates_request_with(Constants.methodName,Constants.requestBody);
			TC01_PostReq_Program.user_calls_with_http_request(ConfigReader.getGlobalValue("postendpoints"), ConfigReader.getGlobalValue("method"));
			TC01_PostReq_Program.user_receives_status_code_with_response_body_and_verify_program_id_using_request_method(Constants.endpoints);
		}
}
