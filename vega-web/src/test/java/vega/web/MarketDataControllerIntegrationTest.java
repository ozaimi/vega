package vega.web;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vega.Application;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringApplicationConfiguration(classes = {Application.class,MarketDataServiceMockConfig.class})
@WebIntegrationTest({"server.port=0", "management.port=0"})
@TestExecutionListeners(inheritListeners = false, listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
@Test(groups="slow")
public class MarketDataControllerIntegrationTest extends AbstractTestNGSpringContextTests {


    private MockMvc mockMvc;
    @Autowired
    private FilterChainProxy filterChainProxy;


    @Autowired
    private WebApplicationContext wac;

    @BeforeMethod
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).dispatchOptions(true).addFilters(filterChainProxy).build();
    }

    @Test
    public void testGet() throws Exception {

        // when
        ResultActions result = mockMvc.perform(get("/MarketData/get/" + 1).header("X-Authorization","sami"));

        // then
        result.andDo(print()).andExpect(status().isOk()).andExpect(content().string("{\"id\":null,\"version\":null,\"marketDataDefId\":0,\"value\":666,\"date\":null}"));
    }

    @Test
    public void testGet1() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}