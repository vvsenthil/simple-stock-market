package com.jpmorgan.api.stock_market.impl.repository.trade;

import com.jpmorgan.api.stock_market.repository.TradeRepository;
import com.jpmorgan.model.Entity;
import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.api.stock_market.impl.repository.RepositoryImpl;
import org.joda.time.DateTime;

import java.util.List;
import java.util.stream.Collectors;

public class TradeRepositoryImpl extends RepositoryImpl<Long, Trade> implements TradeRepository {

    @Override
    public List<Trade> lastMinutes(final int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes is negative value");
        }

        final DateTime now = DateTime.now(Entity.DEFAULT_TIME_ZONE);
        final DateTime difference = now.minusMinutes(minutes);

        return list()
                .stream()
                .filter(entity -> isBeforeOrEqual(difference, entity.getTimestamp()))
                .collect(Collectors.toList());
    }

    private boolean isBeforeOrEqual(final DateTime difference, final DateTime timestamp) {
        return difference.isBefore(timestamp) || difference.isEqual(timestamp);
    }
}
