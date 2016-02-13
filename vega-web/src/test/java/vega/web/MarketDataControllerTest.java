package vega.web;

import org.testng.annotations.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpHeaders;
import vega.model.MarketData;
import vega.service.MarketDataService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Matchers.*;

@Test(groups="fast")
public class MarketDataControllerTest {

    @InjectMocks
    private MarketDataController marketDataController;

    @Mock
    private MarketDataService marketDataService;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test(){
        List<MarketData> result = new ArrayList<>();



            MarketData marketData = new  MarketData();
            marketData.setId((long) 5545);
            marketData.setVersion((long) 0);
            marketData.setValue(new BigDecimal(5554));
            marketData.setMarketDataDefId(87787);
            marketData.setDate(new Date());
            result.add(marketData);



        result.add(new MarketData());

        Mockito.when(marketDataService.findMarketDataByDefId(anyList())).thenReturn(result);


        MarketData r = marketDataController.get(1,new HttpHeaders());


    }
}
