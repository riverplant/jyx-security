package com.river.core.MultiThreads.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsDemo extends AbstractQueuedSynchronizer {

	private static final long serialVersionUID = 4289909407337308966L;
	private int value;
	private MyAqsLock msLock = new MyAqsLock();

	public int next() {
		msLock.lock();
		try {
			Thread.sleep(300);
			return value++;
		} catch (InterruptedException e) {
			throw new RuntimeException();
		} finally {
			msLock.unlock();
		}
	}
	
	public void a() {
		msLock.lock();
		System.out.println("a");
		b();
		msLock.unlock();
	}
	
	public void b() {
		msLock.lock();
		System.out.println("b");
		msLock.unlock();
	}

	public static void main(String[] args) {
		AqsDemo ad = new AqsDemo();

		new Thread(new Runnable() {
			@Override
			public void run() {
				ad.a();//测试重入锁
			}
		}).start();
		
		
	}
}
