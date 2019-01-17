package proxy;

public class MyInterfaceImpl implements myInterface {
	@Override
	public void action() {
		System.out.println("我是动态被代理类");
	}
}
