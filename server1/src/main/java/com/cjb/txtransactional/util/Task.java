package com.cjb.txtransactional.util;

import java.awt.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chenjiabao on 2019/12/29.
 */
public class Task {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitTask(){
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signalTask(){
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
