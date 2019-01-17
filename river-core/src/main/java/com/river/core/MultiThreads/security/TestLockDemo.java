package com.river.core.MultiThreads.security;
/**
 * 测试MyLock的重入锁:同一个线程拿到锁后可以进入另一把锁不需要再拿锁
 * @author riverplant
 *
 */
public class TestLockDemo {

	MyLock lock = new MyLock();

	public void a() {
		lock.lock();
		System.out.println("a");
		b();//在未释放锁的情况下再次调b-->lock.lock()去重入
		lock.unlock();
	}

	public void b() {
		lock.lock();
		System.out.println("b");
		lock.unlock();
	}
}
