package com.jpmorgan.stock_market.volume_weighted_stock_price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.volume_weighted_stock_price.VolumeWeightedStockPriceCalculatorImpl;
import com.jpmorgan.model.trade.Trade;

public class VolumeWeightedStockPriceCalculatorImplTest {
	private static final double DELTA = 0.001;

	private final VolumeWeightedStockPriceCalculatorImpl volumeWeightedStockPriceCalculator = new VolumeWeightedStockPriceCalculatorImpl();

	@Test
	public void should_return_0_if_list_is_empty() {
		final double expected = 0;

		assertEquals(expected, volumeWeightedStockPriceCalculator.calculate(Collections.emptyList()).doubleValue(),
				DELTA);
	}

	@Test
	public void should_return_0_if_list_is_null() {
		final double expected = 0;

		assertEquals(expected, volumeWeightedStockPriceCalculator.calculate(null).doubleValue(), DELTA);
	}

	@Test
	public void should_return_25_after_calculation() {
		final double expected = 25;
		Trade trade = mock(Trade.class);
		doReturn(BigDecimal.valueOf(25)).when(trade).getTradedPrice();
		doReturn(2l).when(trade).getSharesQuantity();

		List<Trade> trades = Arrays.asList(trade, trade, trade);
		assertEquals(expected, volumeWeightedStockPriceCalculator.calculate(trades).doubleValue(), DELTA);
	}

	@Test
	public void should_return_182_94_after_calculation() {
		final double expected = 182.941;
		Trade trade1 = mock(Trade.class);
		doReturn(BigDecimal.valueOf(25)).when(trade1).getTradedPrice();
		doReturn(2l).when(trade1).getSharesQuantity();

		Trade trade2 = mock(Trade.class);
		doReturn(BigDecimal.valueOf(100)).when(trade2).getTradedPrice();
		doReturn(5L).when(trade2).getSharesQuantity();

		Trade trade3 = mock(Trade.class);
		doReturn(BigDecimal.valueOf(256)).when(trade3).getTradedPrice();
		doReturn(10L).when(trade3).getSharesQuantity();

		List<Trade> trades = Arrays.asList(trade1, trade2, trade3);
		assertEquals(expected, volumeWeightedStockPriceCalculator.calculate(trades).doubleValue(), DELTA);
	}
}
