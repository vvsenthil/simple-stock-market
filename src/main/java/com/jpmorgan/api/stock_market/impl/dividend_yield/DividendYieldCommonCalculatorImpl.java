package com.jpmorgan.api.stock_market.impl.dividend_yield;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockType;

import java.math.BigDecimal;
import java.util.Objects;

public class DividendYieldCommonCalculatorImpl extends DividendYieldCalculatorImpl {

    public static final String NAMED = "DividendYieldCommonCalculator";

    @Override
    public StockType stockType() {
        return StockType.COMMON;
    }

    @Override
    public BigDecimal evaluate(final Stock stock, final BigDecimal price) {
        BigDecimal lastDividend = stock.getLastDividend();

        Objects.requireNonNull(lastDividend, "Last Dividend can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");
        if(lastDividend.equals(BigDecimal.ZERO))
        	return new BigDecimal(0);
        else
        	return lastDividend.divide(price, DEFAULT_MATH_CONTEXT);
    }
}
