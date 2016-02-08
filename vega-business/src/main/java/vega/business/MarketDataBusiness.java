package vega.business;

import vega.model.MarketData;

import java.util.List;

public interface MarketDataBusiness {
    List<MarketData> findMarketDataByDefId(List<Long> marketDataDefIds);
}
