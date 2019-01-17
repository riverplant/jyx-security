package com.river.core.MultiThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建好线程池，之后需要线程可以不在需要创建，直接从线程池中获取，用完后直接还给线程池 类似数据库连接池
 * 
 * @author riverplant
 *
 */
public class XccDemo {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);// 创建了带有10个线程的线程池，相当于直接创建了10个线程
		//ExecutorService threadPool2 = Executors.newCachedThreadPool();//
		for (int i = 0; i < 100; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "运行");
				}

			});// execute
		}//该线程池提交100个线程任务，由10个线程来完成

		threadPool.shutdown();// 线程池销毁
	}
}
