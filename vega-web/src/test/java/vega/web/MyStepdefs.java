package vega.web;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs {

    @Given("^there are (\\d+) cucumbers      # <start> replaced with (\\d+)$")
    public void thereAreCucumbersStartReplacedWith(int arg0, int arg1) throws Throwable {
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
