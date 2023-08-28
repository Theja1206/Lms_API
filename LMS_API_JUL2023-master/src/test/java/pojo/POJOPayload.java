package pojo;

import resources.Constants;

public class POJOPayload 
{
	public static ProgramDetails jsonUsingPOJO () 
	{
		
		
		Constants.program.setProgramName(Constants.programName);
		Constants.program.setProgramDescription(Constants.programDesc);
		Constants.program.setProgramStatus(Constants.programStatus);
				
		return Constants.program;
	}
	public static ProgramDetails  putRequestBody() 
	{
		Constants.program.setProgramName(Constants.updateprogramName);
		Constants.program.setProgramDescription(Constants.updateprogramDesc);
		Constants.program.setProgramStatus(Constants.updateprogramStatus);
				
		return Constants.program;
	}
 
	public static Prog_Req_Bdy requestBody_withId()
	{
		Constants.prog_req_ID.setProgramId(Constants.programId);
		Constants.prog_req_ID.setProgramName(Constants.programName);
		Constants.prog_req_ID.setProgramDescription(Constants.programDesc);
		Constants.prog_req_ID.setProgramStatus(Constants.programStatus);
		
		return Constants.prog_req_ID;
		
	}
	
	
	
}
