package singleTom;

public class SingleTom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleTon singleTon = SingleTon.getInstance();
	}

}

/**
 * 单例,使用同步代码块来保证程序安全
 * 
 * @author riverplant
 *
 */
class SingleTon {
	private SingleTon() {

	}

	private static SingleTon instance = null;

	public static SingleTon getInstance() {
		if (instance == null) {
			synchronized (SingleTon.class) {
				if (instance == null) {
					instance = new SingleTon();
				}
			}
		}//if

		return instance;
	}
}
