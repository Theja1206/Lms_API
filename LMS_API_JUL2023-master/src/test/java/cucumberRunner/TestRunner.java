package cucumberRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features",glue= "stepDefinitions",
                       plugin= {"html:target/Cucumber.html",	
		                    "json:target/Cucumber.json",
		                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		                     "rerun:target/failedScenarios.txt"})
 
public class TestRunner extends AbstractTestNGCucumberTests{ 	
	
	//tags = ("@Batch_Module"),
}

