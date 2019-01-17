package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * @author riverplant
 *
 */
//1.定义接口
interface Subject{
	void action();
}
//被代理类
class RealSubject implements Subject{
	@Override
	public void action() {
		System.out.println("我是被代理类");
	}	
}

//创建动态代理类
class MyInfocationHandler implements InvocationHandler{
    Object obj;//实现了接口的被代理类的对象的申明
    /**
     * 1.给被代理类的对象实例化
     * @param obj
     * @return:返回一个代理类的对象
     */
    public Object blind(Object obj) {
    	this.obj = obj;
    	/**loader:被代理类的classloader
    	 * interfaces:被代理类实现的接口
    	 * h:InvocationHandler接口对象，为当前对象
    	 */
    	return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
    /**
     * Method:action
     */
	@Override//需要重写该方法,当通过代理类的对象发起被重写的方法的调用时，都会转换为对如下的invoke方法的调用
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//每当调用invoke=调用action
		//method方法的返回值,相当于invoke方法调用的是Method方法，invoke的返回值就是method方法执行invoke的返回值
		Object returnVal = method.invoke(obj, args);
		return returnVal;
	}
	
	
}
public class TestProxy {
public static void main(String[] args) {
	//1.被代理类的对象
	RealSubject rs = new RealSubject();
	
	//2.实现了InvocationHandler接口的类的对象
	MyInfocationHandler handler = new MyInfocationHandler();
	
	//3.调用blind()方法，动态返回一个同样实现了rs所在类实现的接口Subject的代理类对象
	Object obj = handler.blind(rs);
	
	Subject sub = (Subject) obj;//此时sub就是代理类的对象
	sub.action();//就会转到MyInfocationHandler实现类的invoke方法的调用
}
}
