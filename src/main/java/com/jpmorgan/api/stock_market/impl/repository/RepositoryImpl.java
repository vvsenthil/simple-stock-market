package com.jpmorgan.api.stock_market.impl.repository;

import com.jpmorgan.model.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import com.jpmorgan.api.stock_market.repository.*;

public class RepositoryImpl<K extends Serializable, V extends Entity<K>> implements Repository<K, V> {
    protected final Map<K, V> REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public long count() {
        return REPOSITORY.values().size();
    }

    @Override
    public V create(final V value) {
        REPOSITORY.put(value.getId(), value);
        return REPOSITORY.get(value.getId());
    }

    @Override
    public V load(final K key) {
        return REPOSITORY.get(key);
    }

    @Override
    public V update(final V value) {
        return create(value);
    }

    @Override
    public boolean delete(final K key) {
        if (!REPOSITORY.containsKey(key)) {
            return false;
        }
        REPOSITORY.remove(key);
        return Objects.isNull(REPOSITORY.get(key));
    }

    @Override
    public List<V> list() {
        return new ArrayList<>(REPOSITORY.values());
    }

    @Override
    public void drop() {
        REPOSITORY.clear();
    }
}
