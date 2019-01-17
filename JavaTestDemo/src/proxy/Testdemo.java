package proxy;

public class Testdemo {

	public static void main(String[] args) {
	//1.实例化一个被代理类
		MyInterfaceImpl mi = new MyInterfaceImpl();
		//实例化一个实现了InvocationHandler接口的类对象
		MyInvocationHandler handler = new MyInvocationHandler();
		Object obj = handler.blind(mi);
		//转化成代理类
		myInterface proxy = (myInterface) obj;
		
		proxy.action();
	}

}
