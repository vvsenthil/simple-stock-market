package com.jpmorgan.api.calculator.dividend_yield;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.api.calculator.Calculator;

import java.math.BigDecimal;

public interface DividendYieldCalculator extends Calculator {
    BigDecimal calculate(Stock stock, BigDecimal price);
}
