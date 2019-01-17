package com.river.core.MultiThreads;
/**
 * 
 * @author riverplant
 *
 */
public class ThreadTest1 implements Runnable {

	@Override // 线程执行部分
	public void run() {
		while (true) {
			try {
				//Thread.sleep(100);
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程" + Thread.currentThread().getName() + "执行了....");
		}

	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(new ThreadTest1());// 创建线程并且指定线程任务
		thread1.start();
	}
}
