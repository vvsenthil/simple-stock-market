package com.jpmorgan.stock_market.dividend_yield;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldCommonCalculatorImpl;
import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockType;

public class DividendYieldCommonCalculatorImplTest {
	private static final double DELTA = 0.001;

    private final DividendYieldCommonCalculatorImpl dividendYieldCommonCalculator = new DividendYieldCommonCalculatorImpl();

    @Test
    public void should_return_common_stock_type() {
        assertNotNull(dividendYieldCommonCalculator);
        assertEquals(StockType.COMMON, dividendYieldCommonCalculator.stockType());
        assertNotEquals(StockType.PREFERRED, dividendYieldCommonCalculator.stockType());
    }

    @Test
    public void should_return_0_5_after_evaluation() {
        final double expected = 0.5;
        final BigDecimal price = BigDecimal.valueOf(2);
        final BigDecimal lastDividend = BigDecimal.valueOf(1);

        Stock stock = mock(Stock.class);
        doReturn(lastDividend).when(stock).getLastDividend();

        assertEquals(expected, dividendYieldCommonCalculator.evaluate(stock, price).doubleValue(), DELTA);
    }

    @Test
    public void should_return_1_after_evaluation() {
        final double expected = 1;
        final BigDecimal price = BigDecimal.valueOf(1);
        final BigDecimal lastDividend = BigDecimal.valueOf(1);

        Stock stock = mock(Stock.class);
        doReturn(lastDividend).when(stock).getLastDividend();

        assertEquals(expected, dividendYieldCommonCalculator.evaluate(stock, price).doubleValue(), DELTA);
    }

    @Test
    public void should_return_50_after_evaluation() {
        final double expected = 50;
        final BigDecimal price = BigDecimal.valueOf(2);
        final BigDecimal lastDividend = BigDecimal.valueOf(100);

        Stock stock = mock(Stock.class);
        doReturn(lastDividend).when(stock).getLastDividend();

        assertEquals(expected, dividendYieldCommonCalculator.evaluate(stock, price).doubleValue(), DELTA);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception_if_stock_is_null() {
        final BigDecimal price = BigDecimal.TEN;

        dividendYieldCommonCalculator.calculate(null, price);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception_if_price_is_null() {
        Stock stock = mock(Stock.class);
        doReturn(BigDecimal.TEN).when(stock).getLastDividend();
        doReturn(StockType.COMMON).when(stock).getType();

        dividendYieldCommonCalculator.calculate(stock, null);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception_if_last_dividend_is_null() {
        final BigDecimal price = BigDecimal.TEN;

        Stock stock = mock(Stock.class);
        doReturn(StockType.COMMON).when(stock).getType();

        dividendYieldCommonCalculator.calculate(stock, price);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegal_argument_exception_if_stock_type_is_wrong() {
        Stock stock = mock(Stock.class);
        doReturn(BigDecimal.TEN).when(stock).getLastDividend();
        doReturn(StockType.PREFERRED).when(stock).getType();

        dividendYieldCommonCalculator.calculate(stock, BigDecimal.TEN);
    }
}
