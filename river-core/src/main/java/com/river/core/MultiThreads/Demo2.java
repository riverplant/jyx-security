package com.river.core.MultiThreads;
/**
 * implements Runnable:作为线程任务存在
 * @author riverplant
 *
 */
public class Demo2 implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName()+"thread running....");
		}
		
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new Demo2());
		t1.setName("Thread1");
		t1.start();
	}
}
