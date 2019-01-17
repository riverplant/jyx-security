package TreadDemos;
/**
 *1.继承Thread实现多线程
 * @author riverplant
 *2.yield():调用此方法的线程释放当前CPU的执行权
 *3.join():在A线程中调用B线程的join方法，此时A线程停止执行，直至B线程执行完毕
 *4.isAlive():判断当前线程是否还存活
 */
public class MyThread2 implements Runnable {

	//2.重写run方法
	public void run() {
		for(int i=0;i<99;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
	public static void main(String[] args) {
		//3.创建子类的对象
		MyThread2 thread1 = new MyThread2();
		//4.调用子类的start(),启动此线程，调用相应的run()方法
		//thread1.start();
		
		//匿名类
		new Thread("匿名线程01"){
			public void run() {
				
			}
		}.start();
	}
	
}
