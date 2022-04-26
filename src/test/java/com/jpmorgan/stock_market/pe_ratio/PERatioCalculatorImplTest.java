package com.jpmorgan.stock_market.pe_ratio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.pe_ratio.PERatioCalculatorImpl;

public class PERatioCalculatorImplTest {
	private static final double DELTA = 0.001;

	private final PERatioCalculatorImpl peRatioCalculator = new PERatioCalculatorImpl();

	@Test
	public void should_return_0_0039_after_calculation() {
		final double expected = 0.0039;
		final BigDecimal dividend = BigDecimal.valueOf(512);
		final BigDecimal price = BigDecimal.valueOf(2);

		assertEquals(expected, peRatioCalculator.calculate(dividend, price).doubleValue(), DELTA);
	}

	@Test
	public void should_return_0_after_calculation() {
		final double expected = 0;
		final BigDecimal dividend = BigDecimal.TEN;
		final BigDecimal price = BigDecimal.ZERO;

		assertEquals(expected, peRatioCalculator.calculate(dividend, price).doubleValue(), DELTA);
	}

	@Test
	public void should_return_1_after_calculation() {
		final double expected = 1;
		final BigDecimal dividend = BigDecimal.TEN;
		final BigDecimal price = BigDecimal.TEN;

		assertEquals(expected, peRatioCalculator.calculate(dividend, price).doubleValue(), DELTA);
	}

	@Test
	public void should_return_500_after_calculation() {
		final double expected = 500;
		final BigDecimal dividend = BigDecimal.ONE;
		final BigDecimal price = BigDecimal.valueOf(500);

		assertEquals(expected, peRatioCalculator.calculate(dividend, price).doubleValue(), DELTA);
	}

	@Test(expected = NullPointerException.class)
	public void should_throw_null_pointer_exception_if_dividend_is_null() {
		peRatioCalculator.calculate(null, BigDecimal.ONE);
	}

	@Test(expected = NullPointerException.class)
	public void should_throw_null_pointer_exception_if_price_is_null() {
		peRatioCalculator.calculate(BigDecimal.ONE, null);
	}
}
