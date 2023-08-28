package resources;

public enum APIEndpoints 
{
	//Program Modules Endpoints 
  GetAllPrograms("/allPrograms"),
  GetProgramByID("/programs/"),
  PostRequestEndpoint("/saveprogram"),
  PutRequestEndpoint("/putprogram/"),
  PutRequest("/program/"),
  DeleteProgram("/deletebyprogid/"),
  DeletebyProgName("/deletebyprogname/"),

  // Batch Module Endpoints 
  PostBatch("/batches"),
  GetAllBatches("/batches"),
  GetByBatchID("/batches/batchId/"),
  GetByBachName("/batches/batchName/"),
  GetByProgID("/batches/program/"),
  PutByBatchID("/batches/"),
  DeleteBatch("/batches/"),
  
  
  GetAllUSersEndpoint("/users/users"),
  
  
  GetgradeByBatchID("/assignmentsubmission/grades/"),
  DeleteSubmission("/assignmentsubmission/"),
  DeleteAssignment("/assignments/")
  
  	;
	private String endpoints;

    APIEndpoints(String endpoints)
    {
	   this.endpoints= endpoints;
    }

    public String getResource()
    {
    	return endpoints;
    }
	
}
