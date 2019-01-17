package com.river.core.MultiThreads;

import java.util.Random;

/**
 * 自旋问题
 * 
 * @author riverplant
 *
 */
public class Demo7 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "开始执行");
				try {
					Thread.sleep(new Random().nextInt(2000));// 随机休息两秒
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "执行完毕");
			}

		}).start();// 1

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "开始执行");
				try {
					Thread.sleep(new Random().nextInt(2000));// 随机休息两秒
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "执行完毕");
			}
		}).start();// 2
		if(Thread.activeCount()!=1) {
			//自旋--->等待
		}
		System.out.println("所有的线程执行完毕");
	}
}
