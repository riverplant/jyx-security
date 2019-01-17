package com.river.core.MultiThreads.security;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 重入锁:从一个synchronized方法中调用另外一个synchronized方法，可以直接调用
 * 当同一个线程拿到同一个对象锁的时候，可以重入,避免了死锁问题
 * @author riverplant
 *
 */
public class SecurityDemo {
    /**
     * 锁为当前对象的实例
     */
	public synchronized void a() {
		System.out.println("a");
		b();
	}
	public synchronized void b() {
		System.out.println("b");
		a();
	}
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				SecurityDemo d = new SecurityDemo();
				d.a();
			}
		});
		t.start();
	}
}
