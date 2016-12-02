package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV8 {
    private SingletonV8() {

    }

    private static class SingletonHolder {
        private static final SingletonV8 INSTANCE = new SingletonV8();
    }

    public static SingletonV8 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
