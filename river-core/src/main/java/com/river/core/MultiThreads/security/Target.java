package com.river.core.MultiThreads.security;

public class Target implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName()+"....");
		}
	}

}
