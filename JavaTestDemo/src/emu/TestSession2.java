package emu;

public class TestSession2 {
public static void main(String[] args) {
	Thread.State[] stats = Thread.State.values();
	
 }
}

interface info{
	public void show();
}

// 枚举类
enum Sesson2 implements info{	
	
	SPRING("SPRING", "春暖花开"),
	SUMMER("SUMMER", "夏日炎炎"),
	AUTOMN("AUTOMN", "秋高气爽"),
	WINNER("WINNER", "白雪皑皑");
	
	private final String sessonName;
	private final String sessonDesc;
	
	//4.创建枚举类的对象
	public String getSessonName() {
		return sessonName;
	}

	public String getSessonDesc() {
		return sessonDesc;
	}
	
	private Sesson2(String sessonName, String sessonDesc) {
		this.sessonName = sessonName;
		this.sessonDesc = sessonDesc;
	}

	@Override
	public void show() {
		Sesson2[]ss = Sesson2.values();
		for(Sesson2 s : ss) {
			System.out.println(s);
		}
		
	}

	
}