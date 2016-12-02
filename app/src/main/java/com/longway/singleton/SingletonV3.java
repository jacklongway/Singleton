package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV3 {
    private static SingletonV3 sInstance;

    private SingletonV3() {

    }

    public static SingletonV3 getInstance() {
        synchronized (SingletonV3.class) {
            if (sInstance == null) {
                sInstance = new SingletonV3();
            }
        }
        return sInstance;
    }
}
