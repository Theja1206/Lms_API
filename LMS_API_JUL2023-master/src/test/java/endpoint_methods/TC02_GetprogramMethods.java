package endpoint_methods;

import java.io.IOException;

import resources.Constants;

public class TC02_GetprogramMethods 
{
	public static void getData(String scenarioName) throws IOException 
	 {
		Constants.scenarioName = scenarioName.substring(0, 4);
		
		Constants.programId = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "ProgramID");
		Constants.invalidProgramId = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "InvalidProgramID");
		Constants.expectedStatus = Integer.parseInt(Constants.excelReaderUtil.getDataFromExcel(scenarioName, "StatusCode"));
		Constants.errorMessage = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "message");
		Constants.successState = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "success");
	 }
	 
}
