package com.jpmorgan.api.stock_market.impl.dividend_yield;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockType;

import java.math.BigDecimal;
import java.util.Objects;
import com.jpmorgan.api.calculator.dividend_yield.*;
public abstract class DividendYieldCalculatorImpl implements DividendYieldCalculator {

    public BigDecimal calculate(final Stock stock, final BigDecimal price) {
        Objects.requireNonNull(stock, "Stock can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");

        if (stockType() != stock.getType()) {
            throw new IllegalArgumentException("Stock has unexpected Stock Type: " + stock.getType());
        }

        return evaluate(stock, price);
    }

    protected abstract StockType stockType();

    protected abstract BigDecimal evaluate(final Stock stock, final BigDecimal price);

}
