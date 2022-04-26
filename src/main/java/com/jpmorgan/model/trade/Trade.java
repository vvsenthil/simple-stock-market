package com.jpmorgan.model.trade;

import com.jpmorgan.model.Entity;
import org.joda.time.DateTime;

import java.math.BigDecimal;
 
public class Trade implements Entity<Long> {
    private final long id;
    private final TradeIndicator indicator;
    private final BigDecimal tradedPrice;
    private final long sharesQuantity;
    private final DateTime timestamp;

    private Trade(Builder builder) {
        this.id = builder.id;
        this.indicator = builder.indicator;
        this.tradedPrice = builder.tradedPrice;
        this.sharesQuantity = builder.sharesQuantity;
        this.timestamp = builder.timestamp;
    }

    public Long getId() {
        return id;
    }

    public TradeIndicator getIndicator() {
        return indicator;
    }

    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }

    public long getSharesQuantity() {
        return sharesQuantity;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (sharesQuantity != trade.sharesQuantity) return false;
        if (indicator != trade.indicator) return false;
        if (timestamp != null ? !timestamp.equals(trade.timestamp) : trade.timestamp != null) return false;
        if (tradedPrice != null ? !tradedPrice.equals(trade.tradedPrice) : trade.tradedPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indicator != null ? indicator.hashCode() : 0;
        result = 31 * result + (tradedPrice != null ? tradedPrice.hashCode() : 0);
        result = 31 * result + (int) (sharesQuantity ^ (sharesQuantity >>> 32));
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trade" +
                " {id: " + id +
                ", indicator: " + indicator +
                ", tradedPrice: " + tradedPrice +
                ", sharesQuantity: " + sharesQuantity +
                ", timestamp: " + timestamp + '}';
    }

    public static class Builder {
        private final long id;
        private TradeIndicator indicator;
        private BigDecimal tradedPrice;
        private long sharesQuantity;
        private final DateTime timestamp = DateTime.now(DEFAULT_TIME_ZONE);

        public Builder(final long id) {
            this.id = id;
        }

        public Builder indicator(final TradeIndicator indicator) {
            this.indicator = indicator;
            return this;
        }

        public Builder tradedPrice(final BigDecimal tradedPrice) {
            this.tradedPrice = tradedPrice;
            return this;
        }

        public Builder sharesQuantity(final long sharesQuantity) {
            this.sharesQuantity = sharesQuantity;
            return this;
        }

        public Trade build() {
            return new Trade(this);
        }
    }
}
