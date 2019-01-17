package Bank;

/**
 * 
 * @author riverplant
 *
 */
public class Account {

	public static void main(String[] args) {
		saveAcount account = new saveAcount();
		Thread t1 = new Thread(account);
		t1.setName("用户1");
		Thread t2 = new Thread(account);
		t2.setName("用户2");
		t1.start();
		t2.start();
	}

}

class saveAcount implements Runnable {
	int total = 0;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (total < 6000) {
					total += 1000;
					try {
						Thread.currentThread().sleep(1000);
						System.out.println(Thread.currentThread().getName() + "存入1000块，目前账户余额为:" + total);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					break;
				}
			}
		}
	}

}
