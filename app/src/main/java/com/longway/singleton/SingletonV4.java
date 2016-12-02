package com.longway.singleton;

/**
 * Created by longway on 16/12/3. Email:longway1991117@sina.com
 */

public class SingletonV4 {
    private static SingletonV4 sInstance;

    private SingletonV4() {

    }

    public static SingletonV4 getInstance() {
        if (sInstance == null) {
            synchronized (SingletonV4.class) {
                if (sInstance == null) {
                    sInstance = new SingletonV4();
                }
            }
        }
        return sInstance;
    }
}
