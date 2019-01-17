package TreadDemos;

/**
 * 3guichets 100 tickets
 * 
 * @author riverplant
 *
 */
public class TestWindows {

	public static void main(String[] args) {

		window w1 = new window();
		window w2 = new window();
		window w3 = new window();

		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");

		w1.start();
		w2.start();
		w3.start();

	}

}

class window extends Thread {
	static int ticket = 100;

	public void run() {
		while (true) {
			if (ticket > 0) {
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "窗口售票:" + ticket-- + "号");
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
