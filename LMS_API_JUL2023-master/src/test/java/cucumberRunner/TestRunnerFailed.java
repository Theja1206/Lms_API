package cucumberRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features="@target//failedScenarios.txt",
		glue="stepDefinitions",
		monochrome =true,
		plugin= {"html:target/Cucumber.html",
				"json:target/Cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} )

public class TestRunnerFailed extends AbstractTestNGCucumberTests {

}

