package vega.business;

import vega.model.MarketData;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MarketDataBusinessImpl implements  MarketDataBusiness{

    @Override
    public List<MarketData> findMarketDataByDefId(List<Long> marketDataDefIds) {
        return null;
    }
}
