package vega.web;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import vega.Application;


@CucumberOptions(plugin = "json:target/cucumber-report.json")
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = {Application.class, MarketDataServiceMockConfig.class})
public class RunCukesTest extends AbstractTestNGCucumberTests {
}
