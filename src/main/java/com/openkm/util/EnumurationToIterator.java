package com.openkm.util;

import java.util.Iterator;
import java.util.Enumeration;

public class EnumurationToIterator<E> implements Iterator {
    private Enumeration<E> enumeration;

    public EnumurationToIterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    public E next() {
        return enumeration.nextElement();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
