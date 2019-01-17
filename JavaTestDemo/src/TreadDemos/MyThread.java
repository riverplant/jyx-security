package TreadDemos;
/**
 *1.继承Thread实现多线程
 * @author riverplant
 *2.yield():调用此方法的线程释放当前CPU的执行权
 *3.join():在A线程中调用B线程的join方法，此时A线程停止执行，直至B线程执行完毕
 *4.isAlive():判断当前线程是否还存活
 *5.sleep(long l):让当前线程睡眠l毫秒
 *6.优先级控制：MAX_RPIORITY (10)  \MIN_PRIORITY(1) \ NORM_PRIORITY(5)
 *7.getPriority():返回线程优先值   \ setPriority(int newPriority)改变线程的优先级 \ 线程创建继承父线程的优先级
 */
public class MyThread extends Thread {

	//2.重写run方法
	public void run() {
		for(int i=0;i<99;i++) {
			try {
				Thread.currentThread().sleep(1000);//被重写的方法不能Throws比父类更大的异常，因为Thread类没有抛异常，所以此处不能抛异常
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		//3.创建子类的对象
		MyThread thread1 = new MyThread();
		thread1.setName("子线程1");
		thread1.setPriority(Thread.MAX_PRIORITY);//设置最大优先级
		//4.调用子类的start(),启动此线程，调用相应的run()方法
		thread1.start();
		Thread.currentThread().setName("主线程");
		for(int i=0;i<100;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			if(i==20) {
				thread1.join();
			}
		}
		
		thread1.isAlive();//判断线程是否已经死了
	}
	
}
