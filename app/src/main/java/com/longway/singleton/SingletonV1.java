package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV1 {
    private static SingletonV1 sInstance;

    private SingletonV1() {

    }

    public static SingletonV1 getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonV1();
        }
        return sInstance;
    }

}
