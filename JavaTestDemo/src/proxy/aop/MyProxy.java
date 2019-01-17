package proxy.aop;

import java.lang.reflect.Proxy;

/**
 * 动态的创建一个代理类的对象
 * @author riverplant
 *
 */
public class MyProxy {

	public static Object getProxyInstance(Object obj) {
		MyInvocation handler = new MyInvocation();
		//handler.setObj(obj);//明确obj为被代理类的对象
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
}
