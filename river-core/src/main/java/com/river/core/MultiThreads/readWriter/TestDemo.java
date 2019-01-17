package com.river.core.MultiThreads.readWriter;

/**
 * 测试读写锁
 * 
 * @author riverplant
 *
 */
public class TestDemo {

	public static void main(String[] args) {
		MapReadWriterDemo demo = new MapReadWriterDemo();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=1;i<100;i++) {
					demo.put("key"+i, "obj"+i);
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=1;i<100;i++) {
					System.out.println(demo.get("key"+i));
				}
			}
			
		}).start();
	}
}
