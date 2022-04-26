package com.jpmorgan.api.stock_market.repository;

import com.jpmorgan.model.Entity;

import java.io.Serializable;
import java.util.List;

public interface Repository<K extends Serializable, V extends Entity> {
    long count();

    V create(V value);

    V load(K key);

    V update(V value);

    boolean delete(K key);

    List<V> list();

    void drop();
}
