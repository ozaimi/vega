package vega.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vega.Application;


//@SpringApplicationConfiguration(classes = {Application.class,MarketDataServiceMockConfig.class})
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = {Application.class})
@WebIntegrationTest(value = {"server.port=0", "management.port=0"},randomPort = true)
@TestExecutionListeners(inheritListeners = false, listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})

public class MyStepdefs extends AbstractTestNGSpringContextTests {

    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy filterChainProxy;


    @Autowired
    private WebApplicationContext wac;

    @BeforeClass
    public void setUp() {



    }

    @Given("^there are (\\d+) cucumbers      # <start> replaced with (\\d+)$")
    public void thereAreCucumbersStartReplacedWith(int arg0, int arg1) throws Throwable {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).dispatchOptions(true).addFilters(filterChainProxy).build();


        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();

        System.out.println("thereAreCucumbersStartReplacedWith ");
    }

    @When("^I eat (\\d+) cucumbers            # <eat> replaced with (\\d+)$")
    public void iEatCucumbersEatReplacedWith(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        System.out.println("iEatCucumbersEatReplacedWith ");
    }

    @Then("^I should have (\\d+) cucumbers    # <left> replaced with (\\d+)$")
    public void iShouldHaveCucumbersLeftReplacedWith(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        System.out.println("iShouldHaveCucumbersLeftReplacedWith ");
    }
}
