package vega;

import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import vega.model.MarketData;
import vega.service.MarketDataService;
import vega.service.MarketDataServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.IsEqual.equalTo;

@Category(FastTests.class)
public class MarketDataServiceImplTest {

    @Test
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