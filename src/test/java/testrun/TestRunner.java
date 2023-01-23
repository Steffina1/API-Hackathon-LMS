package com.nn.api.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)

@CucumberOptions(
plugin = {"pretty", "html:target/cucumber.html"},
monochrome=true,
dryRun = true,
tags = "@tag",
features = {"src/test/resources/feature/Createprogram.feature"},
glue= "stepdefinitions")

public class TestRunner extends AbstractTestNGCucumberTests{

@Override
@DataProvider(parallel = false)
public Object[][] scenarios() {
return super.scenarios();
}
}