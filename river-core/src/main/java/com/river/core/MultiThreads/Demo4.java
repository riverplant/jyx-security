package com.river.core.MultiThreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 创建既有返回值又能抛出异常
 * @author riverplant
 *
 */
public class Demo4 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("正在进行计算.....");
		Thread.sleep(3000);
		return 1;
	}
public static void main(String[] args) {
		Demo4 d = new Demo4();
		FutureTask< Integer> task = new FutureTask<Integer>(d);//将Callable封装到FutureTask< Integer>中，
		/**
		 * public class FutureTask<V> implements RunnableFuture<V> 
		 * public interface RunnableFuture<V> extends Runnable, Future<V>
		 */
		Thread t = new Thread(task);//
		t.start();
		try {
			Integer result = task.get();
			System.out.println("计算结果为:"+result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
