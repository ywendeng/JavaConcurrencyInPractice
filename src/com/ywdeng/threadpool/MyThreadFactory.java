package com.ywdeng.threadpool;

import java.util.concurrent.*;

/**
 * MyThreadFactory
 * <p/>
 * Custom thread factory
 *
 * @author Brian Goetz and Tim Peierls
 */
/**
 * 实现ThreadFactory来定制自己的线程工厂方法
 * @author ywdeng
 *
 */
public class MyThreadFactory implements ThreadFactory {
	//为自己的线程指定一个线程名
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
