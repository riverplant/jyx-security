package com.river.core.jdk8.Lambda.expression;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.river.core.jdk8.Lambda.dao.Apple;
import com.river.core.jdk8.Lambda.dao.ComplexApple;
import com.river.core.jdk8.Lambda.functionInterface.ComplextFunction;

/**
 * Lambda表达式方法推导
 * @author riverplant
 *
 */
public class MethodReference {
	public static void main(String[] args) {
		Consumer<String>consumer = (s)->System.out.println(s);
		useConsumer(consumer,"hello");//Consumer<T> consumer,T t
		useConsumer((s)->System.out.println(s),"hello");//Consumer<T> consumer,T t
		useConsumer(System.out::println,"hello");
		//函数推导
		List<Apple> list = Arrays.asList(new Apple("green",110),new Apple("yellow",90),new Apple("red",120));
		list.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));//Comparator按照颜色的asc码排序
		list.sort(Comparator.comparing(Apple::getColor));
		
		//函数推导
		list.stream().forEach(a -> System.out.println(a));
		list.stream().forEach(System.out::println);
		
		//public interface Function<T, R>
		int value = Integer.parseInt("123");
		Function<String,Integer> f = Integer::parseInt;//使用函数推导
		f.apply("123");
		//获得String中指定位置的字符
		BiFunction<String,Integer,Character>f2 = String::charAt;
		Character c = f2.apply("hello", 2);
		//通过的对象的方法推导
		String str = new String("hello");
		Function<Integer,Character> f3 = str::charAt;
		Character c2 = f3.apply(3);
		//Supplier
		Supplier<String> supplier = String::new;
		String s = supplier.get();
		
		BiFunction<String, Long, Apple> bi = Apple::new;
		Apple a = bi.apply("red", 80L);
		/**
		 * 自定义接收三个参数的functionalInterface
		 */
		ComplextFunction<String,String,Long,ComplexApple> cbi = ComplexApple::new;
		ComplexApple ca = cbi.Apply("green", "apple1", 300L);
	}

	private static <T> void useConsumer(Consumer<T> consumer,T t) {
		consumer.accept(t);
	}
}
