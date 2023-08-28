package resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class Specification 

{
	public static RequestSpecification requestSpecBuilder;
	public static ResponseSpecification responseSpecBuilder;
	public static ResponseSpecification responseSpec;
	static APIEndpoints endpointsAPI;
	public static Response response;
	public static Map<String, Object> expectedHeaders;
	
	public static RequestSpecification requestSpecification() throws IOException
	{
		
		
        if(requestSpecBuilder==null)
		{
		PrintStream log    =new PrintStream(new FileOutputStream("logging.txt"));
		requestSpecBuilder =new RequestSpecBuilder().setBaseUri(ConfigReader.getGlobalValue("baseURI"))
				  .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		         .setContentType(ContentType.JSON).build();
		 return requestSpecBuilder;
		}
		return requestSpecBuilder;
		
	}
		public static String resource(String resource)
		{
			endpointsAPI = APIEndpoints.valueOf(resource);
			String apiEndpoint = endpointsAPI.getResource();
			return apiEndpoint;
			}
		
		public static String getJsonPath(Response response,String key)
		{
			String resp=response.asString();
			JsonPath   js = new JsonPath(resp);
			return js.get(key).toString();
			    }
		
		public static Map<String, Object> headerValue()
		{
			Map<String, Object> expectedHeaders = new HashMap<String, Object>();
			expectedHeaders.put("Server","Cowboy");
			expectedHeaders.put("Connection", "keep-alive");
			expectedHeaders.put("Content-Type","application/json");
			expectedHeaders.put("Via", "1.1 vegur");
			
			return expectedHeaders;
		}
		public static ResponseSpecification responseSpecification(Integer statusCode) throws  IOException
		{			
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Log header value  in specification  line 80:  " + headerValue() );
			
		responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(statusCode).
				              expectHeaders(headerValue()).
				              build();
	    
		return responseSpecBuilder;
		}
	}
	
	
	


