package com.jpmorgan.stock_market.dividend_yield;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import com.jpmorgan.api.calculator.dividend_yield.DividendYieldCalculator;
import com.jpmorgan.api.calculator.dividend_yield.DividendYieldCalculatorFactory;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldCalculatorFactoryImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldCommonCalculatorImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldPreferredCalculatorImpl;
import com.jpmorgan.model.stock.StockType;

public class DividendYieldCalculatorFactoryImplTest {
	private final DividendYieldCalculatorFactory dividendYieldCalculatorFactory =
            new DividendYieldCalculatorFactoryImpl(
                    new DividendYieldCommonCalculatorImpl(),
                    new DividendYieldPreferredCalculatorImpl());

    @Test
    public void should_return_common_instance() {
        final DividendYieldCalculator common = dividendYieldCalculatorFactory.newInstance(StockType.COMMON);

        assertNotNull(common);
        assertEquals(DividendYieldCommonCalculatorImpl.class, common.getClass());
        assertNotEquals(DividendYieldPreferredCalculatorImpl.class, common.getClass());
    }

    @Test
    public void should_return_preferred_instance() {
        final DividendYieldCalculator common = dividendYieldCalculatorFactory.newInstance(StockType.PREFERRED);

        assertNotNull(common);
        assertEquals(DividendYieldPreferredCalculatorImpl.class, common.getClass());
        assertNotEquals(DividendYieldCommonCalculatorImpl.class, common.getClass());
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception() {
        dividendYieldCalculatorFactory.newInstance(null);
    }
}
