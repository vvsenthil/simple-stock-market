package com.jpmorgan.api.stock_market.impl.geometric_mean;

import com.jpmorgan.model.trade.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import com.jpmorgan.api.calculator.geometric_mean.*;

public class GeometricMeanCalculatorImpl implements GeometricMeanCalculator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public BigDecimal calculate(final List<Trade> trades) {
        if (Objects.isNull(trades) || trades.isEmpty()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Trades are empty");
            }

            return BigDecimal.ZERO;
        }

        BigDecimal multiplied = BigDecimal.ONE;

        for (Trade trade : trades) {
            multiplied = multiplied.multiply(trade.getTradedPrice());
        }

        double n = BigDecimal.ONE.divide(BigDecimal.valueOf(trades.size()), DEFAULT_MATH_CONTEXT).doubleValue();
        return BigDecimal.valueOf(Math.pow(multiplied.doubleValue(), n));
    }
}
