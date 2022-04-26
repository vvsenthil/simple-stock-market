package com.jpmorgan.model;

import org.joda.time.DateTimeZone;

import java.io.Serializable;

public interface Entity<ID extends Serializable> extends Serializable {
    DateTimeZone DEFAULT_TIME_ZONE = DateTimeZone.UTC;

    ID getId();
}
