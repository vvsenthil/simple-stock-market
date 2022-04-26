package com.jpmorgan.stock_market.dividend_yield;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldPreferredCalculatorImpl;
import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockType;

public class DividendYieldPreferredCalculatorImplTest {
	private static final double DELTA = 0.001;

    private final DividendYieldPreferredCalculatorImpl dividendYieldPreferredCalculator = new DividendYieldPreferredCalculatorImpl();

    @Test
    public void should_return_preferred_stock_type() {
        assertNotNull(dividendYieldPreferredCalculator);
        assertEquals(StockType.PREFERRED, dividendYieldPreferredCalculator.stockType());
        assertNotEquals(StockType.COMMON, dividendYieldPreferredCalculator.stockType());
    }

    @Test
    public void should_return_0_02_after_evaluation() {
        final double expected = 0.02;
        final BigDecimal price = BigDecimal.valueOf(100);
        final BigDecimal fixedDividend = BigDecimal.valueOf(2);
        final BigDecimal parValue = BigDecimal.valueOf(100);

        Stock stock = mock(Stock.class);
        doReturn(fixedDividend).when(stock).getFixedDividend();
        doReturn(parValue).when(stock).getParValue();

        assertEquals(expected, dividendYieldPreferredCalculator.evaluate(stock, price).doubleValue(), DELTA);
    }

    @Test
    public void should_return_0_0625_after_evaluation() {
        final double expected = 0.0625;
        final BigDecimal price = BigDecimal.valueOf(200);
        final BigDecimal fixedDividend = BigDecimal.valueOf(5);
        final BigDecimal parValue = BigDecimal.valueOf(250);

        Stock stock = mock(Stock.class);
        doReturn(fixedDividend).when(stock).getFixedDividend();
        doReturn(parValue).when(stock).getParValue();

        assertEquals(expected, dividendYieldPreferredCalculator.evaluate(stock, price).doubleValue(), DELTA);
    }

    @Test
    public void should_return_5_after_evaluation() {
        final double expected = 5;
        final BigDecimal price = BigDecimal.valueOf(20);
        final BigDecimal fixedDividend = BigDecimal.valueOf(10);
        final BigDecimal parValue = BigDecimal.valueOf(1000);

        Stock stock = mock(Stock.class);
        doReturn(fixedDividend).when(stock).getFixedDividend();
        doReturn(parValue).when(stock).getParValue();

        assertEquals(expected, dividendYieldPreferredCalculator.evaluate(stock, price).doubleValue(), DELTA);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception_if_stock_is_null() {
        final BigDecimal price = BigDecimal.TEN;

        dividendYieldPreferredCalculator.calculate(null, price);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception_if_price_is_null() {
        Stock stock = mock(Stock.class);
        doReturn(BigDecimal.TEN).when(stock).getFixedDividend();
        doReturn(StockType.PREFERRED).when(stock).getType();

        dividendYieldPreferredCalculator.calculate(stock, null);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception_if_fixed_dividend_is_null() {
        final BigDecimal price = BigDecimal.TEN;

        Stock stock = mock(Stock.class);
        doReturn(StockType.PREFERRED).when(stock).getType();

        dividendYieldPreferredCalculator.calculate(stock, price);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegal_argument_exception_if_stock_type_is_wrong() {
        Stock stock = mock(Stock.class);
        doReturn(BigDecimal.TEN).when(stock).getLastDividend();
        doReturn(StockType.COMMON).when(stock).getType();

        dividendYieldPreferredCalculator.calculate(stock, BigDecimal.TEN);
    }
}
