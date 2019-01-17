package proxy.aop;
/**
 * 被代理类
 * @author riverplant
 *
 */
public class SuperMan implements Human {

	@Override
	public void info() {
		System.out.println("我是超人");
	}

	@Override
	public void fly() {
		System.out.println("我可以飞");
	}

}
