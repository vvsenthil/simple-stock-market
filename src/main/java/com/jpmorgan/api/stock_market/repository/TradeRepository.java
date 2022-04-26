package com.jpmorgan.api.stock_market.repository;

import com.jpmorgan.model.trade.Trade;

import java.util.List;

public interface TradeRepository extends Repository<Long, Trade> {
    List<Trade> lastMinutes(int minutes);
}
