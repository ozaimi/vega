package vega.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import vega.model.MarketData;
import vega.service.MarketDataService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Configuration
public class MarketDataServiceMockConfig {

    @Primary
    @Bean
    public MarketDataService registerMarketDataServiceMock() {
        return new MarketDataServiceMock();
    }

    private class MarketDataServiceMock implements  MarketDataService{

        @Override
        public List<MarketData> findMarketDataByDefId(List<Long> marketDataDefIds) {
            List<MarketData> result =  new ArrayList<>();
            MarketData m =new MarketData() ;
            m.setValue(BigDecimal.valueOf(666L));
            result.add(m);
            return result;
        }
    }
}
