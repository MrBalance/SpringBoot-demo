package com.mrbalance.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yunzhang.du
 * @description:
 * @Date: 2019-06-17 17:38
 */
public class ScheduleThreadPool {
    @Autowired
    ThreadPoolTaskExecutor pool;

    // 线程数量
    private final int THREAD_COUNT = 10;
    // 循环标记
    private AtomicInteger next = new AtomicInteger(0);
    // 计数器
    private CountDownLatch countLatch = new CountDownLatch(THREAD_COUNT);

    public void execute(Runnable run) {
        for (int i = 0; i < pool.getCorePoolSize(); i++) {
            pool.execute(run);
        }
    }

    public void submit(Runnable run) {
        for (int i = 0; i < pool.getCorePoolSize(); i++) {
            pool.submit(run);
        }
    }

    public Future<?> submit(Callable<?> call) {
        return pool.submit(call);
    }

    public CountDownLatch getCountDownLatch() {
        return countLatch;
    }

    public synchronized Object getNext(List<?> list) {
        if (next.intValue() >= list.size()) {
            return null;
        }
        next.incrementAndGet();
        return list.get(next.intValue() - 1);
    }

    @SuppressWarnings("unused")
    private void closeThreadPool(final ThreadPoolTaskExecutor executor) {
        try {
            countLatch.await();
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
