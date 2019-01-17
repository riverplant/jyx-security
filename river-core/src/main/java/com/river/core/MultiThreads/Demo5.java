package com.river.core.MultiThreads;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务来创建多线程
 * 
 * @author riverplant
 *
 */
public class Demo5 {

	public static void main(String[] args) {
		Timer time = new Timer();
		time.schedule(new TimerTask() {// TimerTask也是实现了Runnable接口

			@Override
			public void run() {
				// 实现定时任务
				System.out.println(Thread.currentThread().getName() + "开始执行定时任务");
			}

		}, 0, 1000);// 每隔一秒执行一次，没有延时
	}
}
