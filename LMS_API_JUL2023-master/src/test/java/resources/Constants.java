package resources;

import io.restassured.specification.RequestSpecification;
import pojo.Prog_Req_Bdy;
import pojo.ProgramDetails;
import stepDefinitions.TC01_PostReq_Program;
import utilities.ExcelReader;

public class Constants {

	
	// Common variables
	public static ExcelReader excelReaderUtil;
	public static RequestSpecification request;
	public static String methodName;
	public static String endpoints;
	public static String scenarioName;
	public static ProgramDetails program = new ProgramDetails();
	public static Prog_Req_Bdy prog_req_ID = new Prog_Req_Bdy();
	
	
	
	// Positive Status Code
	
	public static int status;
	public static String expectedCreationTime;
	
	//Negative statusCode & messages
	
	public static String actualErrorMessage;
	public static String responseSuccessState;
	
	
	// expected status
	public static int expectedStatus;
	public static String successState;
	public static int errorstatusCode;
	public static String errorMessage;
	
	// TC01_PostProgram Variables 
	
		public static String requestBody;
		
		public static String programName;
		public static String programDesc;
		public static String programStatus;
		
		public static String actualCreationTime;
		public static String date;
		public static String headerDate;
		public static String modTime;
		public static String programId;
		public static String CreationTime;
		public static String programCreatedTime;
	
	//TC_02 Get Program Variables
	
	public static String invalidProgramId;
	public static String queryType;
	
	// TC03_PutProgram Variables 
	
	public static String sheetPut;
	public static String invalidProgramID;
	public static String updateprogramName;
	public static String updateprogramDesc;
	public static String updateprogramStatus;
	
	public static TC01_PostReq_Program TC01;
	public static String invalidProgName;
	public static String message_1;
	public static String message_2;
	public static String successMessage;
	public static String sheetDelete;
	public static String deleteEndpoint;
	public static String validProgName;
	public static String respProg_Name;
	public static String Prog_Name;
	
	
	//Batch Module
	public static String batchDesp;
	public static String batchName;
	public static String No_of_Cls;
	public static String batchStatus ;
	public static String batchId;		
	
	
	
	
	
	
	
	

	
	
	
	
	
}
