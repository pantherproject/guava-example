package com.panther.inner.inter;

/**
 * Created by panther.dongdong on 2016/1/13.
 */
public interface Selector {
    public boolean end();

    public Object current();

    public void next();
}

