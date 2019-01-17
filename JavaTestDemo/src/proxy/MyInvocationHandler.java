package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
   Object obj;//1.给被代理类的对象实例化
	public Object blind(Object obj) {
		this.obj = obj;
		//2.返回一个代理类的对象
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object val = method.invoke(obj, args);
		return val;
	}

}
