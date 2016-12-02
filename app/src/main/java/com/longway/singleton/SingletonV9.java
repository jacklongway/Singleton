package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV9 {
    private static final SingletonV9 INSTANCE = new SingletonV9();

    private SingletonV9() {

    }

    public static SingletonV9 getInstance() {
        return INSTANCE;
    }
}
