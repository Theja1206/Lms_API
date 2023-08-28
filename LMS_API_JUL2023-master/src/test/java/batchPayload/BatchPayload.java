package batchPayload;

import java.util.HashMap;

import resources.Constants;
public class BatchPayload {
	
		
	
	public static  HashMap<String, String> postBatch() {
		
		HashMap<String , String> req_Body = new HashMap<String, String>();
        
		req_Body.put("batchDescription",Constants.batchDesp);
		req_Body.put("batchName",Constants.batchName);
		req_Body.put("batchNoOfClasses",Constants.No_of_Cls);
		req_Body.put("batchStatus",Constants.batchStatus);
		req_Body.put("programId",Constants.programId);
		
		
		return req_Body;
	}

}
