package annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**@Retention:指定该Annotation可以保留多长时间--> 
 *   RetentionPolicy.SOURCE:编译器直接丢弃这种策略的注解
 *   RetentionPolicy.CLASS:编译器把注解记录在class文件中，运行JVM时不会保留
 *   RetentionPolicy.SOURCE:编译器把注解记录在class文件中，运行JVM时会保留注解，程序可以通过反射获取该注解
 *   
 * @Target:指定被修饰的Annotation能修饰哪些程序元素,该注解包含一个名为value的成员变量
 *   ANNOTATION_TYPE: 注解只能修饰注解，不能修饰其他的东西
     CONSTRUCTOR: 注解只能修饰构造方法
     FIELD: 注解只能修饰属性(成员变量)
     LOCAL_VARIABLE: 注解只能修饰局部变量
     METHOD: 注解只能修饰方法
     PACKAGE: 注解只能修饰包
     PARAMETER: 注解只能修饰方法的参数
     TYPE: 注解只能修饰类、接口、枚举

 * @Inherited:被它修饰的Annotation具有继承性，使用了被@Inherited修饰的Annotation,其子类将自动具有该注解
 * 
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	String value() default "hello";
}
