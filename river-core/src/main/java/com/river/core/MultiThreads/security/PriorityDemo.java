package com.river.core.MultiThreads.security;

/**
 * 优先级看饥饿问题
 * 
 * @author riverplant
 *
 */
public class PriorityDemo {

	public static void main(String[] args) {
     Thread t1 = new Thread(new Target());
     t1.setName("线程1");
     //t1.setPriority(10);//最大优先级
     t1.setPriority(Thread.MAX_PRIORITY);
     Thread t2 = new Thread(new Target());
     t2.setName("线程2");//中等
     t2.setPriority(Thread.NORM_PRIORITY);
     Thread t3 = new Thread(new Target());
     t3.setName("线程3");//最小优先级
     t3.setPriority(Thread.MIN_PRIORITY);
     
     t1.start();t2.start();t3.start();
	}
}
