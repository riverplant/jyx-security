package proxy.aop;

public class AopDemo {
public static void main(String[] args) {
	SuperMan sm = new SuperMan();//创建一个被代理类的对象
	MyInvocation handler = new MyInvocation();
	
	Object obj = handler.blind(sm);
//	Object obj = MyProxy.getProxyInstance(sm);//返回一个代理类的对象
	  Human man =  (Human) obj;
	  man.fly();//通过代理类的对象调用重写的抽象方法
	  man.info();
}
}
