package endpoint_methods;

import java.io.IOException;

import resources.ConfigReader;
import resources.Constants;
import stepDefinitions.TC05_DeleteReq_Prog;

public class TC04_DeleteProgramMethods 
{

	public static void deleteData(String scenarioName) throws IOException 
	
	 {
		Constants.expectedStatus = Integer.parseInt(Constants.excelReaderUtil.getDataFromExcel(scenarioName, "StatusCode"));
		Constants.message_1 = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "message1");
		Constants.message_2 = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "message 2");
		Constants.successMessage = Constants.message_1+Constants.programId+ Constants.message_2;
		
	 }
	
	public static void deletemethods() throws IOException
	{
		Constants.deleteEndpoint = ConfigReader.getGlobalValue("deleteEndpoint");
		TC05_DeleteReq_Prog.user_delete_program_using_delete_https_request();
		TC05_DeleteReq_Prog.user_calls_endpoint_with(Constants.deleteEndpoint,Constants.programId);
		
	}
}