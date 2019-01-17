package comunication;

/**
 * 
 * @author riverplant 1.wait 2.notify 3.notifyALl
 *         4.java.lang.Ojbect提供的三个方法只有在synchronized方法或者synchronized代码块中!!!!!
 */
public class Comunication {

	public static void main(String[] args) {
		PrintNum pn = new PrintNum();
		Thread t1 = new Thread(pn);
		Thread t2 = new Thread(pn);
		Thread t3 = new Thread(pn);
		t1.setName("1号线程");
		t2.setName("2号线程");
		t3.setName("3号线程");
		t1.start();
		t2.start();
		t3.start();
	}
}

class PrintNum implements Runnable {
	int num = 1;
   
	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				notify();//握住了锁之后可以唤醒等待的线程
				if (num <= 100) {
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "打印:" + num);
					num++;
				} else {
					break;
				}
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//while

	}

}
