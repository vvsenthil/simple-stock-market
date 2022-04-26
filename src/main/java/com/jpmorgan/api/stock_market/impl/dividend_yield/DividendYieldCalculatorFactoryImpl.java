package com.jpmorgan.api.stock_market.impl.dividend_yield;

import com.jpmorgan.model.stock.StockType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import com.jpmorgan.api.calculator.dividend_yield.*;

public class DividendYieldCalculatorFactoryImpl implements DividendYieldCalculatorFactory {

    private final DividendYieldCalculator dividendYieldCommonCalculator;
    private final DividendYieldCalculator dividendYieldPreferredCalculator;

    @Inject
    public DividendYieldCalculatorFactoryImpl(@Named(DividendYieldCommonCalculatorImpl.NAMED)
                                              DividendYieldCalculator dividendYieldCommonCalculator,
                                              @Named(DividendYieldPreferredCalculatorImpl.NAMED)
                                              DividendYieldCalculator dividendYieldPreferredCalculator) {

        this.dividendYieldCommonCalculator = dividendYieldCommonCalculator;
        this.dividendYieldPreferredCalculator = dividendYieldPreferredCalculator;
    }

    @Override
    public DividendYieldCalculator newInstance(StockType type) {
        Objects.requireNonNull(type, "Stock Type can't be 'Null'");

        switch (type) {
            case COMMON:
                return dividendYieldCommonCalculator;
            case PREFERRED:
                return dividendYieldPreferredCalculator;
            default:
                throw new IllegalArgumentException("Dividend Yield Calculator not found for given type: " + type);
        }
    }
}
