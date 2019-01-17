package proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocation implements InvocationHandler {
	Object obj;
	
	
//	public Object getObj() {
//		return obj;
//	}
//
//	public void setObj(Object obj) {
//		this.obj = obj;
//	}
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
				
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		HumanOutil util = new HumanOutil();
		util.method1();
		// TODO Auto-generated method stub
		Object val = method.invoke(obj, args);//在两个方法中间插入一个动态方法,面向切面
		util.method2();
		return val;
	}

}
