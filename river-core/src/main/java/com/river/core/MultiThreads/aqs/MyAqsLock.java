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
public class MyAqsLock implements Lock {
	private LockHelper lockHelper = new LockHelper();
	/**
	 * 锁的实现委托给AbstractQueuedSynchronizer的子类LockHelper来实现
	 * @author riverplant 独占模式只需要重写tryAcquire和tryRelease
	 */
	private class LockHelper extends AbstractQueuedSynchronizer {//同步器
		private static final long serialVersionUID = 5836141078069186161L;
		List<QueueObject>waitingThreads = new ArrayList<QueueObject>();
		@Override // 实现独占模式
		protected boolean tryAcquire(int arg) {// 进入arg = 1 条线程
			QueueObject queueObject = new QueueObject();//队列对象
			synchronized (this) {
				waitingThreads.add(queueObject);//先放入等待队列
			}
			// 需要重写
			// 1.如果第一个线程进来，可以拿到锁
			// 判断是第一个线程进来还是其他线程
			int state = getState();// 获得当前同步线程状态
			/**
			 *  如果进入的线程不是第一个线程 同时 该线程不是当前线程
			 */
			while (state != 0 && Thread.currentThread() != getExclusiveOwnerThread()) {
				try {
					wait();
				} catch (InterruptedException e) {
					synchronized (this) {
						waitingThreads.remove(queueObject);//先放入等待队列
					}
					e.printStackTrace();
				}
			}
			synchronized (this) {
				setState(state+arg);
				setExclusiveOwnerThread(Thread.currentThread());// 设置当前线程为拿到锁的线程
				waitingThreads.remove(queueObject);//先放入等待队列
			}
			return true;
		}//try

		/**
		 * state:当前同步线程状态
		 */
		@Override // 实现独占模式
		protected boolean tryRelease(int arg) {// 释放arg = 1 条同步线程
		
			// 锁的获取和释放肯定是一一对应,所以调用此方法的线程一定是当前线程
			// 如果当前线程不是获取锁的线程，直接报异常
			if (Thread.currentThread() != getExclusiveOwnerThread()) {
				throw new RuntimeException();
			}
			int state = getState() - arg;// arg=1
			boolean flag = false;
			if (state == 0) {
				setExclusiveOwnerThread(null);// 当前线程为空
				if (waitingThreads.size() > 0) {
					waitingThreads.get(0).doNotify();
				}
				flag = true;
			}
			setState(state);
			return flag;
		}
		/**
		 * 添加一个返回ConditionObject方法
		 * @return
		 */
		Condition newCondition() {
			return new ConditionObject();
		}
	}// LockHelper

	@Override // 锁的实现由lockHelper来完成
	public void lock() {
		lockHelper.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		lockHelper.acquireInterruptibly(1);

	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return lockHelper.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return lockHelper.tryAcquireNanos(1, unit.toNanos(time));// 该方法需要long nanosTimeout
	}

	@Override
	public void unlock() {
		lockHelper.release(1);// 释放一个锁
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return lockHelper.newCondition();
	}

}
