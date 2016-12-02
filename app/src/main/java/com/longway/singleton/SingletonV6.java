package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV6 {
    private static volatile SingletonV6 sInstance;

    private SingletonV6() {

    }

    protected Object readResolve() {
        return getInstance();
    }

    public static SingletonV6 getInstance() {
        if (sInstance == null) {
            synchronized (SingletonV6.class) {
                if (sInstance == null) {
                    sInstance = new SingletonV6();
                }
            }
        }
        return sInstance;
    }
}
