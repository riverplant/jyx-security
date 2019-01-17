package reflection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Properties;
import org.junit.jupiter.api.Test;

/**
 * 代理设计模式:使用一个代理将对象包装起来，用该代理对象取代原始对象
 * 任何对原始对象的调用都要通过代理，代理对象决定是否以及何时将方法调用转到原始对象上
 * @author riverplant
 *
 */
public class TestReflection {
	/**
	 * 通过反射获取Person类的所有相关信息
	 */
	@Test
	public void testFiled() {
		Class clazz = Person.class;
		Field[] fields2 = clazz.getFields();// 获得运行时类及其父类中声明为public的属性
		Field[] fields = clazz.getDeclaredFields();// 获得运行时类本身声明的所有属性

		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println(field.getName());
			System.out.println(field.getModifiers());// 获取属性权限修饰符：0.defualt 1.public 2.private
			System.out.println(Modifier.toString(field.getModifiers()));
			System.out.println(field.getType().getName());
			// System.out.println(field.get);//获取属性变量类型
		} // for
		/**
		 * 获取运行时类的方法
		 */
		Method[] methods2 = clazz.getMethods();// 获得运行时类及其父类所有声明为public的方法
		Method[] methods = clazz.getDeclaredMethods();// 获得运行时类中所有本身声明的所有方法
		for (Method m : methods) {
			System.out.println(m.getName());
		}

		/**
		 * 1.注解 2.权限修饰符 3.返回值类型 4.方法名 5.参数列表 6.异常
		 */

		Method[] methodss = clazz.getDeclaredMethods();// 获得运行时类中所有本身声明的所有方法
		for (Method m : methodss) {
			Annotation[] annotation = m.getAnnotations();
			for (Annotation a : annotation) {
				System.out.println(a);
			}

			// 2.权限修饰符
			Modifier.toString(m.getModifiers());

			// 3.返回值类型
			System.out.println(m.getReturnType().getName());

			// 4.方法名
			m.getName();

			// 5.形参列表
			Class[] params = m.getParameterTypes();

			for (Class p : params) {
				System.out.println(p.getName());
			}

			// 6.异常
			Class[] exceptions = m.getExceptionTypes();
			if (exceptions != null && exceptions.length != 0) {
				for (Class p : exceptions) {
					System.out.println(p.getName());
				}
			}
			
		
		}
		/**
		 * 2.构造器
		 */
		Constructor[] cons = clazz.getDeclaredConstructors();

		for(Constructor c : cons) {
			System.out.println(c);
		}
		/**
		 * 3.获取运行时类的父类
		 */
		Type type1 = clazz.getGenericSuperclass();
		
		ParameterizedType param = (ParameterizedType) type1;
		//获取父类的泛型
		Type[] ars = param.getActualTypeArguments();
		((Class)ars[0]).getName();
		
		/**
		 * 4.获得实现的接口
		 */
		Class[] interfaces = clazz.getInterfaces();
		for(Class inter : interfaces) {
			
		}
		/**
		 * 5.获取所在的包
		 * 
		 */
		clazz.getPackage();
		
		/**
		 * 6.获取类的注解
		 */
		Annotation[] annotations = clazz.getAnnotations();//声明为runtim的注解才可以获取
		
		
	}// testFiled

	// 如何获取Class的实例
	@Test
	public void test4() throws Exception {
		// 1.调用运行时类本身的.class属性
		Class clazz1 = Person.class;
		clazz1.getName();
		Class sclass = String.class;
		sclass.getName();

		// 2.通过运行时类的对象获取
		Person p = new Person();
		Class clazz2 = p.getClass();

		// 3.通过Class的静态方法
		String className = "reflection.Person";
		Class clazz3 = Class.forName(className);
		//
		/**
		 * 1.需要对应的运行时类有空参的构造器!!!!!!!!!!!!!!!!!!! 2.需要构造器的权限修饰符权限要够。default需要在同一个包下
		 * 3.创建类的时候尽量保留一个空参构造器，因为子类继承父类时也是调父类的空参构造器!!!!!!!!!!!!!!!!!!!!!!!
		 */
		Object obj = clazz3.newInstance();// 创建实例
		Person pp = (Person) obj;
		// 4.通过类的加载器!!!!ClassLoader:把类装载进内存
		/**
		 * 1.Bootstrap ClassLoader :引导类加载器，加载核心类库，无法直接获取 2.Extension ClassLoader :
		 * 扩展类加载器，负责jre/lib/ext目录下的jar包 3.System ClassLoader:
		 * 系统类加载器(自定义)，负责java-classpath目录下的类与jar包，最常用的加载器
		 */
		ClassLoader cl = this.getClass().getClassLoader();
		Class clazz4 = cl.loadClass(className);

	}

	@Test
	public void test5() throws Exception {
		ClassLoader classLoader1 = ClassLoader.getSystemClassLoader();
		ClassLoader classLoaderparent = classLoader1.getParent();

		//
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream("jdbc.propertie");// com\\riverplant\\...点用//来代替---->配置文件在包里
		FileInputStream fis = new FileInputStream(new File("jdbc.propertie"));// 如果配置文件直接在工程下可以用这种方法
		Properties po = new Properties();
		po.load(is);
		String username = po.getProperty("user");
		System.out.println(username);

	}

	/**
	 * java.lang.Class是反射的源头 public final Class getClass()//Object类中定义了该方法，被所有的子类继承
	 * 每一次运行时类只加载一次，如果缓存区里有了就不需要再加载 1.创建对应的运行时类对象Person p = clazz.newInstance();
	 * 2.获取运行时类的完整结果(属性、方法、构造器、父类、所在的包、注解、异常...) 3.调用对应的运行时类指定的结果(属性、方法、构造器) Field
	 * f1 = clazz.getDeclaredField("name");//name f1.setAccessible(true); f1.set(p,
	 * "jie"); Field[]fields = clazz.getFields(); 4.反射的应用:动态代理
	 */
	@Test
	public void test3() {

	}

	// 通过反射，创建一个类的对象，调用其中的结构
	@Test
	public void test1() throws Exception {
		Class<Person> clazz = Person.class;
		// 1.创建clazz对应的运行时类Person类的对象
		Person p = clazz.newInstance();
		// 通过以下方法可以获得表示为private的属性!!!!!!!!!!!!!!!!
		Field f1 = clazz.getDeclaredField("name");// name
		f1.setAccessible(true);
		f1.set(p, "jie");
		Field[] fields = clazz.getFields();

		// 2.获取方法
		Method[] methods = clazz.getMethods();

		Method m1 = clazz.getDeclaredMethod("show");
		m1.setAccessible(true);
		m1.invoke(p);
		/**
		 * String.class:写传入的参数类型,有几个写几个,通过,隔开
		 */
		Method m2 = clazz.getDeclaredMethod("display", String.class);
		m2.setAccessible(true);
		m2.invoke(p, "中国");// 传入参数
	}
}
