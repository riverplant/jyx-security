package reflection;
/**
 * 静态代理
 * @author riverplant
 *
 */
//1.先定义接口
interface CouthProduct{
	void productCloth();
}
//被代理类
class NikeCouthFactory implements CouthProduct{

	@Override
	public void productCloth() {
		System.out.println("Nike工厂");		
	}	
}
//代理类
class ProxyFactory implements CouthProduct{
	CouthProduct cf;
	/**
	 * 代理类的构造器,创建代理类的对象时，实际传入一个被代理类的对象
	 * @param cf
	 */
	public ProxyFactory(CouthProduct cf) {
		this.cf = cf;
	}
	@Override
	public void productCloth() {
		System.out.println("代理类开始执行");
		cf.productCloth();
	}	
}
public class TestCoudProduct {
	public static void main(String[] args) {
		NikeCouthFactory nike = new NikeCouthFactory();//创建被代理类的对象
		ProxyFactory proxy = new ProxyFactory(nike);//创建代理类的对象
		proxy.productCloth();
	}	
}
