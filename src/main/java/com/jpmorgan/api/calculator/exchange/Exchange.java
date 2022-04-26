package com.jpmorgan.api.calculator.exchange;

import com.jpmorgan.model.Exchangeable;

import java.io.Serializable;

public interface Exchange<K extends Serializable, V extends Exchangeable> {
    V get(K key);
}
