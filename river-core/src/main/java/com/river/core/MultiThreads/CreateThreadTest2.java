package com.river.core.MultiThreads;
/**
 * 
 * @author riverplant
 *
 */
public class CreateThreadTest2 extends Thread {

	public CreateThreadTest2(String name) {
		super(name);
	}

	@Override // 线程执行部分
	public void run() {
		while (!isInterrupted()) {
			System.out.println("线程" + Thread.currentThread().getName() + "执行了....");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		Thread thread1 = new CreateThreadTest2("线程1");// 创建线程并且指定线程任务
		Thread thread2 = new CreateThreadTest2("线程2");// 创建线程并且指定线程任务
		thread1.start();
		thread2.start();
		thread1.interrupt();
	}
}
