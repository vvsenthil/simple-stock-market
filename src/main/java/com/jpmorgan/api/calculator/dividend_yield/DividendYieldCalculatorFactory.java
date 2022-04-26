package com.jpmorgan.api.calculator.dividend_yield;

import com.jpmorgan.model.stock.StockType;


public interface DividendYieldCalculatorFactory {
    DividendYieldCalculator newInstance(StockType type);
}
