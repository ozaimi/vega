package vega.service;

import vega.model.MarketData;
import java.util.List;

public interface MarketDataService {

    List<MarketData> findMarketDataByDefId(List<Long> marketDataDefIds);
}
