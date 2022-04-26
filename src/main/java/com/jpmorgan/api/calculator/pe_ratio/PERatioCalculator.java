package com.jpmorgan.api.calculator.pe_ratio;

import com.jpmorgan.api.calculator.Calculator;

import java.math.BigDecimal;

public interface PERatioCalculator extends Calculator {
    BigDecimal calculate(BigDecimal dividend, BigDecimal price);
}
