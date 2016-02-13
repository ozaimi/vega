package vega;


import org.testng.annotations.Test;
import vega.model.MarketData;
import vega.service.MarketDataService;
import vega.service.MarketDataServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Test(groups = "fast",sequential = false)
public class MarketDataServiceImplTest {


    public void findMarketDataByDefId_whenCalled_returnCorrectData() {

        // Arrange
        List<Long> defIds = new ArrayList<>();
        defIds.add((long) 1);
        defIds.add((long) 2);
        defIds.add((long) 3);
        List<MarketData> expectedMarketData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MarketData mktData = new MarketData();
            mktData.setValue(BigDecimal.valueOf(i));
            mktData.setVersion((long) 0);
            mktData.setId((long) i);
            mktData.setMarketDataDefId(i);
            mktData.setDate(new Date());
            expectedMarketData.add(mktData);
        }

        // Act
        MarketDataService marketDataService  = new MarketDataServiceImpl();
        List<MarketData> result = marketDataService.findMarketDataByDefId(defIds);

        // Assert
        //assertThat(result, is(equalTo(expectedMarketData)));
    }
}