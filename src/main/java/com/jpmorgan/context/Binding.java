package com.jpmorgan.context;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.jpmorgan.api.calculator.dividend_yield.DividendYieldCalculator;
import com.jpmorgan.api.calculator.dividend_yield.DividendYieldCalculatorFactory;
import com.jpmorgan.api.stock_market.StockMarket;
import com.jpmorgan.api.stock_market.impl.StockMarketImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldCalculatorFactoryImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldCommonCalculatorImpl;
import com.jpmorgan.api.stock_market.impl.dividend_yield.DividendYieldPreferredCalculatorImpl;
import com.jpmorgan.api.calculator.geometric_mean.GeometricMeanCalculator;
import com.jpmorgan.api.stock_market.impl.geometric_mean.GeometricMeanCalculatorImpl;
import com.jpmorgan.api.calculator.pe_ratio.PERatioCalculator;
import com.jpmorgan.api.stock_market.impl.pe_ratio.PERatioCalculatorImpl;
import com.jpmorgan.api.calculator.volume_weighted_stock_price.VolumeWeightedStockPriceCalculator;
import com.jpmorgan.api.stock_market.impl.volume_weighted_stock_price.VolumeWeightedStockPriceCalculatorImpl;
import com.jpmorgan.api.stock_market.exchange.stock.StockExchange;
import com.jpmorgan.api.stock_market.impl.stock_exchange.StockExchangeImpl;
import com.jpmorgan.api.stock_market.repository.TradeRepository;
import com.jpmorgan.api.stock_market.impl.repository.trade.TradeRepositoryImpl;

 
public final class Binding extends AbstractModule {

    @Override
    protected void configure() {
        bind(StockMarket.class).to(StockMarketImpl.class);
        bind(StockExchange.class).to(StockExchangeImpl.class);
        bind(TradeRepository.class).to(TradeRepositoryImpl.class);

        bind(DividendYieldCalculatorFactory.class).to(DividendYieldCalculatorFactoryImpl.class);
        bind(DividendYieldCalculator.class)
                .annotatedWith(Names.named(DividendYieldCommonCalculatorImpl.NAMED))
                .to(DividendYieldCommonCalculatorImpl.class);
        bind(DividendYieldCalculator.class)
                .annotatedWith(Names.named(DividendYieldPreferredCalculatorImpl.NAMED))
                .to(DividendYieldPreferredCalculatorImpl.class);

        bind(PERatioCalculator.class).to(PERatioCalculatorImpl.class);

        bind(VolumeWeightedStockPriceCalculator.class).to(VolumeWeightedStockPriceCalculatorImpl.class);

        bind(GeometricMeanCalculator.class).to(GeometricMeanCalculatorImpl.class);
    }
}
