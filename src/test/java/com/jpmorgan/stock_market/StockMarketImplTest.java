package com.jpmorgan.stock_market;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.jpmorgan.api.calculator.dividend_yield.DividendYieldCalculatorFactory;
import com.jpmorgan.api.calculator.geometric_mean.GeometricMeanCalculator;
import com.jpmorgan.api.calculator.pe_ratio.PERatioCalculator;
import com.jpmorgan.api.calculator.volume_weighted_stock_price.VolumeWeightedStockPriceCalculator;
import com.jpmorgan.api.stock_market.exchange.stock.StockExchange;
import com.jpmorgan.api.stock_market.impl.StockMarketImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldCommonCalculatorImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldPreferredCalculatorImpl;
import com.jpmorgan.api.stock_market.repository.TradeRepository;
import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.model.stock.StockType;
import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.model.trade.TradeIndicator;

@RunWith(MockitoJUnitRunner.class)
public class StockMarketImplTest {

	private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(100);

	@Mock
	private StockExchange stockExchange;
	@Mock
	private TradeRepository tradeRepository;
	@Mock
	private DividendYieldCalculatorFactory dividendYieldCalculatorFactory;
	@Mock
	private DividendYieldCommonCalculatorImpl dividendYieldCommonCalculator;
	@Mock
	private DividendYieldPreferredCalculatorImpl dividendYieldPreferredCalculator;
	@Mock
	private PERatioCalculator peRatioCalculator;
	@Mock
	private VolumeWeightedStockPriceCalculator volumeWeightedStockPriceCalculator;
	@Mock
	private GeometricMeanCalculator geometricMeanCalculator;

	private StockMarketImpl stockMarket;

	@Before
	public void beforeEachMethod() {
		stockMarket = new StockMarketImpl(stockExchange, tradeRepository, dividendYieldCalculatorFactory,
				peRatioCalculator, volumeWeightedStockPriceCalculator, geometricMeanCalculator);

		doReturn(dividendYieldCommonCalculator).when(dividendYieldCalculatorFactory).newInstance(StockType.COMMON);
		doReturn(BigDecimal.valueOf(0.2)).when(dividendYieldCommonCalculator).calculate(any(), any());

		final long sharesQuantity = 1L;

		final Trade trade = mock(Trade.class);

		doReturn(BigDecimal.valueOf(1.1)).when(peRatioCalculator).calculate(any(), any());
		doReturn(sharesQuantity).when(tradeRepository).count();
		doReturn(trade).when(tradeRepository).create(any());
		doReturn(Arrays.asList(trade)).when(tradeRepository).lastMinutes(anyInt());
		doReturn(BigDecimal.valueOf(2.2)).when(volumeWeightedStockPriceCalculator).calculate(anyListOf(Trade.class));
		doReturn(BigDecimal.valueOf(3.3)).when(geometricMeanCalculator).calculate(anyListOf(Trade.class));
	}

	@Test(expected = NullPointerException.class)
	public void should_throw_null_pointer_exception_if_stock_symbol_is_null() {
		stockMarket.process(null, DEFAULT_PRICE);
	}

	@Test(expected = NullPointerException.class)
	public void should_throw_null_pointer_exception_if_price_is_null() {
		stockMarket.process(StockSymbol.POP, null);
	}

	@Test(expected = NullPointerException.class)
	public void should_throw_null_pointer_exception_if_stock_not_found() {
		stockMarket.process(StockSymbol.POP, DEFAULT_PRICE);
	}

	@Test
	public void should_precess_stock() {
		final StockSymbol stockSymbol = StockSymbol.POP;

		Stock stock = mock(Stock.class);
		doReturn(stock).when(stockExchange).get(stockSymbol);
		doReturn(StockType.COMMON).when(stock).getType();

		final Trade trade = stockMarket.process(stockSymbol, DEFAULT_PRICE);

		assertNotNull(trade);
	}
}
