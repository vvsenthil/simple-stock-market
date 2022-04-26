package com.jpmorgan.stock_market.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.repository.trade.TradeRepositoryImpl;
import com.jpmorgan.model.Entity;
import com.jpmorgan.model.trade.Trade;

public class TradeRepositoryImplTest {
	private final TradeRepositoryImpl tradeRepository = new TradeRepositoryImpl();

	@Before
	public void beforeEachTest() {
		final DateTime now = DateTime.now(Entity.DEFAULT_TIME_ZONE);
		final DateTime minutesAgo9 = now.minusMinutes(9);
		final DateTime minutesAgo14 = now.minusMinutes(14);
		final DateTime minutesAgo60 = now.minusMinutes(60);

		Trade trade1 = mock(Trade.class);
		doReturn(1L).when(trade1).getId();
		doReturn(now).when(trade1).getTimestamp();
		tradeRepository.create(trade1);

		Trade trade2 = mock(Trade.class);
		doReturn(2L).when(trade2).getId();
		doReturn(minutesAgo9).when(trade2).getTimestamp();
		tradeRepository.create(trade2);

		Trade trade3 = mock(Trade.class);
		doReturn(3L).when(trade3).getId();
		doReturn(minutesAgo14).when(trade3).getTimestamp();
		tradeRepository.create(trade3);

		Trade trade4 = mock(Trade.class);
		doReturn(4L).when(trade4).getId();
		doReturn(minutesAgo60).when(trade4).getTimestamp();
		tradeRepository.create(trade4);
	}

	@Test
	public void should_return_trades_in_past_10_minutes() {
		final List<Trade> trades = tradeRepository.lastMinutes(10);

		assertEquals(2, trades.size());
	}

	@Test
	public void should_return_trades_in_past_15_minutes() {
		final List<Trade> trades = tradeRepository.lastMinutes(15);

		assertEquals(3, trades.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_minutes_negative_value() {
		tradeRepository.lastMinutes(-1);
	}

	@After
	public void afterEachTest() {
		tradeRepository.drop();
	}
}
