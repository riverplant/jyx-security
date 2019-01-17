package com.river.core.MultiThreads.readWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * @author riverplant 实现对map的读写 为了保证操作的安全，必须使用读写锁
 */
public class MapReadWriterDemo {

	private Map<String, Object> map = new HashMap<String, Object>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();// 定义一个读写锁

	private Lock rlock = lock.readLock();// 获得读锁

	private Lock wlock = lock.writeLock();// 获得写锁

	/**
	 * 读
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		rlock.lock();
		System.out.println(Thread.currentThread().getName() + "在执行读操作...");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			rlock.unlock();
			System.out.println(Thread.currentThread().getName() + "读操作执行完毕");
		}

	}

	/**
	 * 写
	 * 
	 * @param key
	 * @param obj
	 */
	public void put(String key, Object obj) {
		wlock.lock();
		System.out.println(Thread.currentThread().getName() + "在执行写操作...");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put(key, obj);
		} finally {
			wlock.unlock();
			System.out.println(Thread.currentThread().getName() + "写操作执行完毕");
		}

	}
}
