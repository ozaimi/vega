package vega.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vega.model.MarketData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarketDataServiceImpl implements MarketDataService{

  //  @Autowired
//    private MarketDataBusiness marketDataBusiness;

    @Override
    public List<MarketData> findMarketDataByDefId(List<Long> marketDataDefIds) {
        List<MarketData> result = new ArrayList<>();

        int i = 0;
        for (Long marketDataDefId:marketDataDefIds) {
            MarketData marketData = new  MarketData();
            marketData.setId((long) i);
            marketData.setVersion((long) 0);
            marketData.setValue(new BigDecimal(i));
            marketData.setMarketDataDefId(i);
            marketData.setDate(new Date());
            result.add(marketData);
            i++;
        }

        return result;
    }
}
