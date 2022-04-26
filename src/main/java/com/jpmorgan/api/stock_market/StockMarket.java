package com.jpmorgan.api.stock_market;

import java.math.BigDecimal;

import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.model.trade.Trade;

public interface StockMarket {
	Trade process(StockSymbol stockSymbol, BigDecimal price);
}
