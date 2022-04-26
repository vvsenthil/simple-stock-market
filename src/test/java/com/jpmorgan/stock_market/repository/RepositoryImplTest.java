package com.jpmorgan.stock_market.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.api.stock_market.impl.repository.RepositoryImpl;
import com.jpmorgan.model.Entity;

public class RepositoryImplTest {
	private final RepositoryImpl<Long, Entity<Long>> tradeRepository = new RepositoryImpl<>();

	@Before
	public void beforeEachTest() {
		Entity<Long> entity1 = mockEntity();
		doReturn(1L).when(entity1).getId();
		tradeRepository.create(entity1);

		Entity<Long> entity2 = mockEntity();
		doReturn(2L).when(entity2).getId();
		tradeRepository.create(entity2);

		Entity<Long> entity3 = mockEntity();
		doReturn(3L).when(entity3).getId();
		tradeRepository.create(entity3);

		Entity<Long> entity4 = mockEntity();
		doReturn(4L).when(entity4).getId();
		tradeRepository.create(entity4);
	}

	@Test
	public void should_count_entities() {
		assertEquals(4, tradeRepository.count());
	}

	@Test
	public void should_create_entity() {
		assertEquals(4, tradeRepository.count());

		Entity<Long> entity = mockEntity();
		doReturn(5L).when(entity).getId();

		final Entity<Long> created = tradeRepository.create(entity);

		assertEquals(entity, created);
		assertEquals(5, tradeRepository.count());
	}

	@Test
	public void should_load_entity() {
		assertEquals(4, tradeRepository.count());

		final Entity<Long> loaded = tradeRepository.load(1L);

		assertNotNull(loaded);
		assertEquals(4, tradeRepository.count());
	}

	@Test
	public void should_update_entity() {
		assertEquals(4, tradeRepository.count());

		final Entity<Long> loaded = tradeRepository.load(1L);
		final Entity<Long> updated = tradeRepository.update(loaded);

		assertEquals(loaded, updated);
		assertEquals(4, tradeRepository.count());
	}

	@Test
	public void should_delete_entity() {
		assertEquals(4, tradeRepository.count());

		boolean isDeleted = tradeRepository.delete(1l);

		assertEquals(true, isDeleted);
		assertEquals(3, tradeRepository.count());

		isDeleted = tradeRepository.delete(1l);

		assertEquals(false, isDeleted);
		assertEquals(3, tradeRepository.count());
	}

	@Test
	public void should_return_entities_list() {
		assertEquals(4, tradeRepository.list().size());
	}

	@After
	public void afterEachTest() {
		tradeRepository.drop();
	}

	@SuppressWarnings("unchecked")
	private Entity<Long> mockEntity() {
		return (Entity<Long>) mock(Entity.class);
	}
}
