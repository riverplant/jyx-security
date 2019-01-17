package Transaction;

/**
 * 
 * @author riverplant 生产者:Productor ，当产品达到20，会被店员叫停 店员:Clerk :只能有20个产品
 *         消费者:Customer,如果没有产品了，会被店员叫停
 * 
 *
 */
public class ProduceConsumer {
	public static void main(String[] args) {
		Clerk clerk = new Clerk(0);
		Productor pro = new Productor(clerk);
		Customer cus = new Customer(clerk);
		Thread t1 = new Thread(pro);
		//Thread t3 = new Thread(pro);
		Thread t2 = new Thread(cus);
		Thread t4 = new Thread(cus);
		t1.setName("生产者1");
		t2.setName("消费者1");
		t4.setName("消费者2");
		//t3.setName("生产者2");
		t1.start();
		t2.start();
		//t3.start();
		t4.start();
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
			synchronized (this) {

				if (clerk.getStok() >= 20) {
					try {
						System.out.println(Thread.currentThread().getName() + "wait");
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						Thread.currentThread().sleep(100);
						clerk.setStok(clerk.getStok() + 1);
						System.out
								.println(Thread.currentThread().getName() + "生产出一个产品，当前共有产品" + (clerk.getStok()) + "个");
						System.out.println(Thread.currentThread().getName() + "唤醒其它线程");
						notify();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					;
				}

			}

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

}

class Customer implements Runnable {
	Clerk clerk;

	public Customer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		while (true) {
  			synchronized (this) {
 				if (clerk.getStok() <= 0) {
					try {
						System.out.println(Thread.currentThread().getName() + "wait");
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						Thread.currentThread().sleep(100);
						clerk.setStok(clerk.getStok() - 1);
						System.out
								.println(Thread.currentThread().getName() + "拿走一个产品，当前共有产品" + (clerk.getStok()) + "个");
						System.out.println(Thread.currentThread().getName() + "唤醒其它线程");
						notify();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					;
				}

			}

		} // while
	}

}