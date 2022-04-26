package com.jpmorgan.api.calculator.volume_weighted_stock_price;

import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.api.calculator.Calculator;

import java.math.BigDecimal;
import java.util.List;

 
public interface VolumeWeightedStockPriceCalculator extends Calculator {
    BigDecimal calculate(List<Trade> trades);
}
