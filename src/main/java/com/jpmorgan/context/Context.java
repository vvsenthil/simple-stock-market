package com.jpmorgan.context;

import com.google.inject.Guice;
import com.google.inject.Injector;

public final class Context {
    private static final Injector INJECTOR = Guice.createInjector(new Binding());

    public static Injector getInjector() {
        return INJECTOR;
    }
}
