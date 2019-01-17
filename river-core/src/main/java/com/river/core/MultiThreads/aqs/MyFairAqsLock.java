package com.river.core.MultiThreads.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Lock接口
 * 
 * @author riverplant 在使用lock 和unlock的时候必须给方法加上synchronized,才能准确的得到你想要的那把锁!!!!!
 *
 */
public class MyFairAqsLock {

	private boolean isLocked = false;// 是否已经锁定
	private Thread lockingThread = null;// 锁定线程
	private List<QueueObject> waitingThreads = new ArrayList<>();// 等待线程列表

	/**
	 * 锁定
	 */
	public void lock() {
		QueueObject queueObject = new QueueObject();//队列对象
		boolean isLockedForThisThread = true;//当前线程拿到锁
		synchronized (this) {
			waitingThreads.add(queueObject);//先放入等待队列
		}
		while (isLockedForThisThread) {// 是当前线程拿到锁
			synchronized (this) {
				isLockedForThisThread = isLocked;
				if (!isLockedForThisThread) {// false
					isLocked = true;
					waitingThreads.remove(queueObject);//移除等待队列
					lockingThread = Thread.currentThread();
					return;
				}
			}
		} // while
		try {
			queueObject.doWait();// 如果不是当前线程拿到锁，需要等待
		} catch (Exception e) {
			synchronized (this) {
				waitingThreads.remove(queueObject);
			}
			throw (e);
		}

	}// try

	public void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}

}
