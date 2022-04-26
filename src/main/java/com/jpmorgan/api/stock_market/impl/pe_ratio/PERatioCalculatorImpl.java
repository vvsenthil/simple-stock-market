package com.jpmorgan.api.stock_market.impl.pe_ratio;

import java.math.BigDecimal;
import com.jpmorgan.api.calculator.pe_ratio.PERatioCalculator;
import java.util.Objects;

public class PERatioCalculatorImpl implements PERatioCalculator {

    @Override
    public BigDecimal calculate(final BigDecimal dividend, final BigDecimal price) {
        Objects.requireNonNull(dividend, "Dividend can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");
        if(dividend.equals(BigDecimal.ZERO))
        	return new BigDecimal(0);
        else 
        	return price.divide(dividend, DEFAULT_MATH_CONTEXT);
    }
}
