package com.river.core.MultiThreads.security;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 数值生成器 使用原子类
 * 
 * @author riverplant
 *
 */
public class AtomicSequence {

	private AtomicInteger value = new AtomicInteger(0);//初始值为0
	
	private int[] s = {2,1,4,6};
	
	AtomicIntegerArray arr = new AtomicIntegerArray(s);
	
	AtomicReference<User> user = new AtomicReference<User>();
	
	AtomicIntegerFieldUpdater<User> u =  AtomicIntegerFieldUpdater.newUpdater(User.class, "old");//更新User类的old字段
	
	/**
	 * synchronized 放在普通方法上，内置锁就是当前类的实例, 保证了当前方法只有一个线程进来，保证了该方法的原子性
	 * 
	 * @return
	 */
	public int getNext() {// 使用原子类方法的自增++
		//value.incrementAndGet();//++value
		arr.getAndIncrement(2);//++arr[2]
		arr.getAndAdd(2, 10);//arr[2]+=10
		User user = new User();
		u.getAndIncrement(user);
		
		return value.getAndIncrement();//value++
	}

	/**
	 * synchronized 修饰静态方法,内置锁是当前Class字节码对象 sequance.class
	 * 
	 * @return
	 */
	public int getPrevious() {
		//value.decrementAndGet();//--value
		return value.getAndDecrement();//原子类自减方法value--
	}

	/**
	 * synchronized修饰代码块
	 * 
	 * @return
	 */
	public int xx() {
		// monitorenter
		synchronized (AtomicSequence.class) {// 可以指定一个对象作为锁
			if (value.get() > 0) {
				return value.get();
			} else {
				return 0;
			}
		}
	}// monitorexit

	public static void main(String[] args) {
		AtomicSequence s = new AtomicSequence();
		/**
		 * 线程1
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + ":" + s.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
		/**
		 * 线程2
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + ":" + s.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
		/**
		 * 线程3
		 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() + ":" + s.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
	}// main
}
