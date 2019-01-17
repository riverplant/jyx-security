
package comunication;

/**
 * 
 * @author riverplant 生产者:Productor ，当产品达到20，会被店员叫停 店员:Clerk :只能有20个产品
 *         消费者:Customer,如果没有产品了，会被店员叫停
 * 1.是否涉及到多线程:是，生产者与消费者
 * 2.是否有共享数据:考虑线程安全
 * 3.共享数据:产品的数量
 * 4.是否有通信，生产者与消费者
 *
 */
public class Comunication2 {
	public static void main(String[] args) {
		Clerk clerk = new Clerk(0);
		Productor pro = new Productor(clerk);
		Customer cus = new Customer(clerk);
		Thread t1 = new Thread(pro);//生产者的线程
		Thread t3 = new Thread(pro);//生产者2的线程
		Thread t2 = new Thread(cus);//消费者的线程
		t1.setName("生产者1");
		t3.setName("生产者2");
		t2.setName("消费者1");
		t1.start();
		t2.start();
		t3.start();
	}
}

class Productor implements Runnable {
	Clerk clerk;

	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		while (true) {

			clerk.addProduct();
		} // true

	}

}

class Clerk {// 店员
	private int stok;

	public Clerk(int stok) {
		this.stok = stok;
	}

	public int getStok() {
		return stok;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}

	public synchronized void addProduct() {// 生产
		if (stok >= 20) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Thread.currentThread().sleep(100);
				stok++;
				System.out.println(Thread.currentThread().getName() + "生产" + stok + "个产品");
				notifyAll();// 唤醒消费者
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public synchronized void customeProduct() {// 消费
		if (stok <=1) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Thread.currentThread().sleep(1000);
				stok--;
				System.out.println(Thread.currentThread().getName() + "消费第" + stok + "个产品");
				notifyAll();// 唤醒生产
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

class Customer implements Runnable {
	Clerk clerk;

	public Customer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		while (true) {
			clerk.customeProduct();

		}
	}

}