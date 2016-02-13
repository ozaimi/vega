package vega.web;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;


@CucumberOptions(plugin = "json:target/cucumber-report.json")
@Test(groups="slow")
public class RunCukesTest extends AbstractTestNGCucumberTests {
}
