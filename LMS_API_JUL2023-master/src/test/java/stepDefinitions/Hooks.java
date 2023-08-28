package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.BeforeAll;
import resources.ConfigReader;
import resources.Constants;
import utilities.ExcelReader;
public class Hooks 

{

	@BeforeAll
	public static void before() throws IOException
	{
	   
		Constants.excelReaderUtil = new ExcelReader(ConfigReader.getGlobalValue("ProgramFile"));	
	}
	
	
}
