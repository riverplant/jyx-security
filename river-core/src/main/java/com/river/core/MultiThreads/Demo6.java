package com.river.core.MultiThreads;

/**
 * 死锁问题
 * 
 * @author riverplant
 *
 */
public class Demo6 {
	private Object obj1 = new Object();
	private Object obj2 = new Object();

	public void a() throws Exception {
		synchronized(obj1) {
			Thread.sleep(100);
			synchronized(obj2) {
				System.out.println("a");
			}
		}
	}
	
	public void b() throws Exception {
		synchronized(obj2) {
			Thread.sleep(100);
			synchronized(obj1) {
				System.out.println("b");
			}
		}
	}
	public static void main(String[] args) {
		Demo6 demo = new Demo6();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					demo.a();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					demo.b();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
	}
}
