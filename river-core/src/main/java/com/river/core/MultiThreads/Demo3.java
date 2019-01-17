package com.river.core.MultiThreads;

/**
 * 通过匿名内部类创建线程
 * 
 * @author riverplant
 *
 */
public class Demo3 {

	public static void main(String[] args) {
		/*
		 * new Thread() { public void run() { System.out.println("threat.start..."); }
		 * }.start();
		 * 
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * }
		 * 
		 * }).start();
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("继承run方法");
			}

		}) {
			public void run() {
				System.out.println("重写run方法");
			}

		}.start();
	}

}
