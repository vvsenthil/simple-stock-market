package com.jpmorgan.api.stock_market.impl.stock_exchange;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.model.stock.StockType;
import com.jpmorgan.api.stock_market.exchange.stock.StockExchange;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class StockExchangeImpl implements StockExchange {
    private static final Map<StockSymbol, Stock> STOCKS = new HashMap<>();

    static {
        STOCKS.put(StockSymbol.TEA, new Stock.Builder(1, StockSymbol.TEA, StockType.COMMON)
                .lastDividend(BigDecimal.ZERO)
                .parValue(BigDecimal.valueOf(100))
                .build());

        STOCKS.put(StockSymbol.POP, new Stock.Builder(2, StockSymbol.POP, StockType.COMMON)
                .lastDividend(BigDecimal.valueOf(8))
                .parValue(BigDecimal.valueOf(100))
                .build());

        STOCKS.put(StockSymbol.ALE, new Stock.Builder(3, StockSymbol.ALE, StockType.COMMON)
                .lastDividend(BigDecimal.valueOf(23))
                .parValue(BigDecimal.valueOf(60))
                .build());

        STOCKS.put(StockSymbol.GIN, new Stock.Builder(4, StockSymbol.GIN, StockType.PREFERRED)
                .lastDividend(BigDecimal.valueOf(8))
                .fixedDividend(BigDecimal.valueOf(2))
                .parValue(BigDecimal.valueOf(100))
                .build());

        STOCKS.put(StockSymbol.JOE, new Stock.Builder(5, StockSymbol.JOE, StockType.COMMON)
                .lastDividend(BigDecimal.valueOf(13))
                .parValue(BigDecimal.valueOf(250))
                .build());
    }

    public Stock get(final StockSymbol symbol) {
        return STOCKS.get(symbol);
    }
}
