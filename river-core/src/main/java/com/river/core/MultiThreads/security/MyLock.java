package com.river.core.MultiThreads.security;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Lock接口
 * 
 * @author riverplant 在使用lock 和unlock的时候必须给方法加上synchronized,才能准确的得到你想要的那把锁!!!!!
 *
 */
public class MyLock implements Lock {
	private boolean isLocked = false;// 判断是否拿到锁
	// 为了实现重入锁，需要记录拿到锁的线程
	private Thread lockBy;
	private int lockCount;// 记录锁的数量

	/**
	 * 
	 */
	@Override
	public synchronized void lock() {// 上锁,起到线程进来就拿到一把锁，让其它线程无法进入的效果
		// 判断当前访问线程是否是拿到锁的线程
		Thread currentThread = Thread.currentThread();
		// Thread.activeCount()
		// 使用while达到自旋效果
		while (isLocked && currentThread != lockBy) {// 当已经有线程拿到锁&&拿到锁的线程不是当前线程
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isLocked = true;// 第一个线程进来改变isLocked为加锁
		lockBy = Thread.currentThread();
		lockCount++;// 计数器自增
	}

	@Override // 解锁
	public synchronized void unlock() {
		if (lockBy == Thread.currentThread()) {
			lockCount--;
			if (lockCount == 0) {
				notify();// 唤醒线程
				isLocked = false;// 将isLocked改变为解状态锁
			}

		}

	}

	@Override // 中断
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override // 通过tryLock获取锁，如果获取成功，返回true,获取失败返回false
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
