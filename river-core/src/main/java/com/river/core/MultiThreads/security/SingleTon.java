package com.river.core.MultiThreads.security;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式
 * 
 * @author riverplant
 *
 */
public class SingleTon {
	// 1.先私有化构造函数
	private SingleTon() {
	}

	// 2.懒加载
	private static volatile SingleTon instance = null;//volatile:减少虚拟机优化引起的指令重排序问题，避免安全性问题

	/**
	 * 获取实例 偏向锁:每个别的线程来竞争就一直拿着锁不放 轻量级锁:多个线程都可以进入，但是当第一个线程没有释放锁之前，其它的线程只能是自旋，会导致性能下降
	 * 
	 * @return
	 */
	// public static synchronized SingleTon getInstance()
	// {//为了提高性能不能再这里加synchronized来保证安全
	public static SingleTon getInstance() {
		if (instance == null) {
			synchronized (SingleTon.class) {//将初始化放入同步代码块，提升性能
				if (instance == null) {//双重检查加锁
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					instance = new SingleTon();
				}
			}
		}

		return instance;
	}

	public static void main(String[] args) {
		ExecutorService poolThread = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i++) {
			poolThread.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ":" + SingleTon.getInstance());
				}

			});
		}
		poolThread.shutdown();
	}
}
