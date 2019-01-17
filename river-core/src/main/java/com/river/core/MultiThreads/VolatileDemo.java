package com.river.core.MultiThreads;

/**
 * 
 * @author riverplant
 *
 */
public class VolatileDemo {

	public volatile boolean run = false;

	public static void main(String[] args) {
		VolatileDemo demo = new VolatileDemo();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					System.err.println(Thread.currentThread().getName() + "执行了第" + i + "次");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				demo.run = true;
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!demo.run) {
					//不执行
				}
				for (int i = 1; i <= 10; i++) {
					System.err.println(Thread.currentThread().getName() + "执行了第" + i + "次");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				demo.run = true;
			}
		}).start();
	}

}
