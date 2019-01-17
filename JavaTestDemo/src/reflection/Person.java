package reflection;
@MyAnnotation(value="riverplant")
public class Person extends Creature<String> implements Comparable ,MyInterface{
	
	private static final long serialVersionUID = 2521668487253079170L;
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@MyAnnotation(value="showMethod")
	public void show() {
		System.out.println("我是一个人");
	}

	public void display(String nation) throws Exception {
		System.out.println("我的国籍是" + nation);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 内部类
	 * @author riverplant
	 *
	 */
	class Bird{
		
	}
}


