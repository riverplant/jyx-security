package emu;

public class TestSession {

}

// 枚举类
class Sesson {
	// 1.提供类的属性，生命为private final类型
	private final String sessonName;
	private final String sessonDesc;

	// 2.私有化构造器，声明为final的属性，在构造器中初始化
	private Sesson(String sessonName, String sessonDesc) {
		this.sessonName = sessonName;
		this.sessonDesc = sessonDesc;
	}

	//3. 通过公共方法来调用属性
	public String getSessonName() {
		return sessonName;
	}

	public String getSessonDesc() {
		return sessonDesc;
	}

	@Override
	public String toString() {
		return "Sesson [sessonName=" + sessonName + ", sessonDesc=" + sessonDesc + "]";
	}

	public void show() {
		System.out.println("这个是一个季节类");
	}
	
	//4.创建枚举类的对象
	public static final Sesson Spring = new Sesson("SPRING", "春暖花开");
	public static final Sesson summer = new Sesson("SUMMER", "夏日炎炎");
	public static final Sesson automn = new Sesson("AUTOMN", "秋高气爽");
	public static final Sesson winner = new Sesson("WINNER", "白雪皑皑");
}