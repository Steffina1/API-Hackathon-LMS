package testrunner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			plugin= {"pretty", "html:Reports/htmlreport.html", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
			features = {"src/test/resources/features"},
			glue= "stepdefinitions",
			monochrome=true,
			dryRun=true
			)
	@Test
	public class Testrunhtml extends AbstractTestNGCucumberTests{
		@Override
		 
		@DataProvider(parallel = false)
		public Object[][] scenarios(){
	return super.scenarios();
	}

}
