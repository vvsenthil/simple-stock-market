package com.jpmorgan.api.calculator.geometric_mean;

import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.api.calculator.Calculator;

import java.math.BigDecimal;
import java.util.List;


public interface GeometricMeanCalculator extends Calculator {
    BigDecimal calculate(List<Trade> trades);
}
