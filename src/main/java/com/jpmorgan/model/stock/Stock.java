package com.jpmorgan.model.stock;

import com.jpmorgan.model.Exchangeable;

import java.math.BigDecimal;


public class Stock implements Exchangeable {
    private final long id;
    private final StockSymbol symbol;
    private final StockType type;
    private final BigDecimal lastDividend;
    private final BigDecimal fixedDividend;
    private final BigDecimal parValue;

    private Stock(Builder builder) {
        this.id = builder.id;
        this.symbol = builder.symbol;
        this.type = builder.type;
        this.lastDividend = builder.lastDividend;
        this.fixedDividend = builder.fixedDividend;
        this.parValue = builder.parValue;
    }

    public Long getId() {
        return id;
    }

    public StockSymbol getSymbol() {
        return symbol;
    }

    public StockType getType() {
        return type;
    }

    public BigDecimal getLastDividend() {
        return lastDividend;
    }

    public BigDecimal getFixedDividend() {
        return fixedDividend;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (fixedDividend != null ? !fixedDividend.equals(stock.fixedDividend) : stock.fixedDividend != null)
            return false;
        if (lastDividend != null ? !lastDividend.equals(stock.lastDividend) : stock.lastDividend != null) return false;
        if (parValue != null ? !parValue.equals(stock.parValue) : stock.parValue != null) return false;
        if (symbol != stock.symbol) return false;
        if (type != stock.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (lastDividend != null ? lastDividend.hashCode() : 0);
        result = 31 * result + (fixedDividend != null ? fixedDividend.hashCode() : 0);
        result = 31 * result + (parValue != null ? parValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stock" +
                " {id: " + id +
                ", symbol: " + symbol +
                ", type: " + type +
                ", lastDividend: " + lastDividend +
                ", fixedDividend: " + fixedDividend +
                ", parValue: " + parValue + '}';
    }

    public static class Builder {
        private final long id;
        private final StockSymbol symbol;
        private final StockType type;
        private BigDecimal lastDividend;
        private BigDecimal fixedDividend;
        private BigDecimal parValue;

        public Builder(final long id, final StockSymbol symbol, final StockType type) {
            this.id = id;
            this.symbol = symbol;
            this.type = type;
        }

        public Builder lastDividend(final BigDecimal lastDividend) {
            this.lastDividend = lastDividend;
            return this;
        }

        public Builder fixedDividend(final BigDecimal fixedDividend) {
            this.fixedDividend = fixedDividend;
            return this;
        }

        public Builder parValue(final BigDecimal parValue) {
            this.parValue = parValue;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }
    }
}
