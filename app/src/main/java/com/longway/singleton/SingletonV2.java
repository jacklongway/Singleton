package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV2 {
    private static SingletonV2 sInstance;

    private SingletonV2() {

    }

    public static synchronized SingletonV2 getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonV2();
        }
        return sInstance;
    }
}
