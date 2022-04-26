package com.jpmorgan.stock_market.stock_exchange;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.stock_exchange.StockExchangeImpl;
import com.jpmorgan.model.stock.StockSymbol;

public class StockExchangeImplTest {
	private final StockExchangeImpl stockExchange = new StockExchangeImpl();

	@Test
	public void should_return_stock_by_stock_symbol() {
		assertNotNull(stockExchange.get(StockSymbol.POP));
	}

	@Test
	public void should_return_null_if_stock_symbol_is_null() {
		assertNull(stockExchange.get(null));
	}
}
