package endpoint_methods;

import java.io.IOException;

import resources.Constants;

public class TC07_PostBatchMethods 

{


	
	public static void postBatchData(String scenarioName) throws IOException 
	{
		
		Constants.batchDesp = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "batchDesp");
		Constants.batchName = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "batchName");
		Constants.No_of_Cls = Constants.excelReaderUtil.getDataFromExcel(scenarioName, "No_Of_Cls");
	    Constants.batchStatus =Constants.excelReaderUtil.getDataFromExcel(scenarioName, "batchStatus");
	}

	public static void statusCode(String scenarioName) throws NumberFormatException, IOException 
	{
		Constants.expectedStatus = Integer.parseInt(Constants.excelReaderUtil.getDataFromExcel(scenarioName, "Status Code"));
	}
	
}
