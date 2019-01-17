package com.river.core.MultiThreads.security;

/**
 * 数值生成器
 * 
 * @author riverplant
 *
 */
public class Sequence {

	private static int value;
	
    /**
     * synchronized 放在普通方法上，内置锁就是当前类的实例,
     *  保证了当前方法只有一个线程进来，保证了该方法的原子性
     * @return
     */
	public synchronized int getNext() {//加上同步方法使得多线程更安全
		return value++;
	}
	/**
	 * synchronized 修饰静态方法,内置锁是当前Class字节码对象
	 * sequance.class
	 * @return
	 */
	public static synchronized int getPrevious(){
		return value--;
	}
	/**
	 * synchronized修饰代码块
	 * @return
	 */
	public int xx() {
		//monitorenter
		synchronized(Sequence.class) {//可以指定一个对象作为锁
			if(value>0) {
				return value;
			}else {
				return 0;
			}
		}
	}//monitorexit

	public static void main(String[] args) {
		Sequence s = new Sequence();
		// while(true) {
		// System.out.println(s.getNext());
		// }
        /**
         * 线程1
         */
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
                System.out.println(Thread.currentThread().getName()+":"+s.getNext());
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
                System.out.println(Thread.currentThread().getName()+":"+s.getNext());
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
                System.out.println(Thread.currentThread().getName()+":"+s.getNext());
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}

		}).start();
	}//main
}
