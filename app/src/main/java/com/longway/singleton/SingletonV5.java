package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV5 {
    private static SingletonV5 sInstance;

    private SingletonV5() {

    }

    public static SingletonV5 getInstance() {
        if (sInstance == null) {
            synchronized (SingletonV5.class) {
                sInstance = new SingletonV5();
            }
        }
        return sInstance;
    }
}
