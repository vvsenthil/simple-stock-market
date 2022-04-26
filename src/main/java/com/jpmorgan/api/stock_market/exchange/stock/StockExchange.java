package com.jpmorgan.api.stock_market.exchange.stock;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.api.calculator.exchange.Exchange;


public interface StockExchange extends Exchange<StockSymbol, Stock> {
}
