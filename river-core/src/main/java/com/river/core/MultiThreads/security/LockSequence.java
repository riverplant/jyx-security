package com.river.core.MultiThreads.security;

/**
 * 数值生成器 Lock类
 * 
 * @author riverplant
 *
 */
public class LockSequence {

	private int value ;
	MyLock lock = new MyLock();//所有线程公用一把锁
	MyLock lock2 = new MyLock();//所有线程公用一把锁
	/**
	 * synchronized 放在普通方法上，内置锁就是当前类的实例, 保证了当前方法只有一个线程进来，保证了该方法的原子性
	 * 
	 * @return
	 */
	public int getNext() {// 使用原子类方法的自增++
		
		lock.lock();//锁定,类似进入同步代码块,起到线程进来就拿到一把锁，让其它线程无法进入的效果
		int reuslt = value++;
		lock.unlock();//解锁,退出同步代码块，保证了线程安全问题，相当于释放锁，让其它线程可以进入
		return reuslt;
	}

	/**
	 * synchronized 修饰静态方法,内置锁是当前Class字节码对象 sequance.class
	 * 
	 * @return
	 */
	public int getPrevious() {
		//value.decrementAndGet();//--value
		return value--;
	}

	/**
	 * synchronized修饰代码块
	 * 
	 * @return
	 */
	public int xx() {
		// monitorenter
		synchronized (LockSequence.class) {// 可以指定一个对象作为锁
			if (value > 0) {
				return value;
			} else {
				return 0;
			}
		}
	}// monitorexit

	public static void main(String[] args) {
		LockSequence s = new LockSequence();
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
