package TreadDemos;

/**
 * 3guichets 100 tickets 守护线程:用来服务用户线程，thread.setDaemon(true):可以把用户线程变成守护线程 用户线程
 * 
 * @author riverplant
 * 
 *         notify()/notifyAll() < -- > wait() resume() < -- > suspend()
 * 
 *         同步机制，实现线程的安全: 1.同步代码块: synchronized (args//同步监视器/锁)
 *         {}//必须用相同的同步代码锁!!!!!!!!!!!!!!!!!!!!!!!!!!!! 哪一个线程获取同步监视器，哪个线程就执行代码
 *         2.同步方法
 */
public class TestWindows3 {

	public static void main(String[] args) {

		window tickets = new window();
		Thread t1 = new Thread(tickets);
		Thread t2 = new Thread(tickets);
		Thread t3 = new Thread(tickets);
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.start();
		t2.start();
		t3.start();

	}

}

class wwindow4 implements Runnable {
	int ticket = 100;// 共享数据
	// Object lock = new Object();//共用一个lock

	public void run() {
		while (true) {
			show();
		}

	}

	public synchronized void show() {//同步方法

		if (ticket > 0) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "窗口售票:" + ticket-- + "号");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
