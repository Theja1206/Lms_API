package endpoint_methods;

import java.io.IOException;

import resources.Constants;

public class TC03_PutProgramMethods {

	

	
	public static void putData(String scenarioName) throws IOException 
	 {
		 // ********* read data from excel and store it in hash map ****//
	   
		Constants.validProgName = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Valid Program Name");
		Constants.updateprogramName = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "update program Name");
		Constants.updateprogramDesc = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "update program desc");
		Constants.updateprogramStatus = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "update Program Status");
		Constants.expectedStatus = Integer.parseInt(Constants.excelReaderUtil.getDataFromExcel(scenarioName, "StatusCode"));
		
		Constants.invalidProgramID = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Invalid Program ID for PUT");
		Constants.invalidProgName = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Invalid Program ID for PUT");
		Constants.errorMessage = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "message");
		Constants.successState = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "success");
		
		 
	 }
}
