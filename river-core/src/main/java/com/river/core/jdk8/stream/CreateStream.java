package com.river.core.jdk8.stream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.river.core.enums.Type;
import com.river.core.jdk8.Lambda.dao.Dish;
import com.river.core.jdk8.Lambda.dao.Obj;

/**
 * Stream学习
 * 
 * @author riverplant
 *
 */
public class CreateStream {
	/**
	 * iterator-->public interface UnaryOperator<T> extends Function<T, T>
	 * 继承Function的方法R apply(T t); 自带一个静态方法:static <T> UnaryOperator<T> identity()
	 * {return t -> t;}
	 * 
	 * @return
	 */
	private static Stream<Integer> createStreamFromIterator() {
		//Stream<Integer> stream = Stream.iterate(0, n ->{return n + 2;} );//
		Stream<Integer> stream = Stream.iterate(0, n ->n + 2 ).limit(10);//产生一个无限Stream:2、4、6、8、10...
		return stream;
	}
	/**
	 * generate(Supplier<T>) -->T get();
	 * @return
	 */
	private static Stream<Double> createStreamFromGenerator(){
		return Stream.generate(Math::random).limit(10);
	}
	/**
	 * 使用generate(Supplier<T>)
	 * @return
	 */
	private static Stream<Obj> createObjStreamFromGenerator(){
		return Stream.generate(new ObjSupplier()).limit(10);
	}

	private static Stream<String> createStreamFromArrays() {
		String[] strs = { "hello", "hello", "hello", "hello", "hello" };
		return Arrays.stream(strs);
	}

	private static Stream<String> createStreamByValues() {
		return Stream.of("hello", "hello", "hello", "hello", "hello");
	}

	/**
	 * 通过文件获得Stream
	 * 
	 * @return
	 */
	private static Stream<String> createStreamFromFile() {
		String filePath = "";
		Path path = Paths.get(filePath);
		// Files.lines(path) 返回一个Stream
		try (Stream<String> lines = Files.lines(path)) {// 写在try中运行完后会自动关闭
			lines.forEach(System.out::println);
			return lines;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unused")
	private static List<Dish> createMenu() {
		// 创建menu
		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
				new Dish("beef", false, 700, Type.MEAT), new Dish("chicken", false, 400, Type.MEAT),
				new Dish("french frits", true, 530, Type.OTHER), new Dish("rice", true, 350, Type.OTHER),
				new Dish("pizza", true, 550, Type.OTHER));
		menu.stream().forEach(System.out::println);
		return menu;
	}

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public static List<String> getDishNameByStream(List<Dish> menu) {
		return menu.parallelStream()// 多线程并行
				.filter(d -> d.getCalories() < 400)// 传入Predicate boolean test(T t)
				.sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName)// 产生另外一个Stream<R> Stream<R>
																					// map(Function<? super T, ? extends
																					// R> mapper);
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * @param menu
	 * @return
	 */
	public static List<Dish> getDishNameByCollections(List<Dish> menu) {
		List<Dish> lowCalories = new ArrayList<Dish>();
		// 1.filter
		for (Dish d : menu) {
			if (d.getCalories() < 400)
				lowCalories.add(d);
		}
		Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));
		Collections.sort(lowCalories, Comparator.comparing(Dish::getCalories));
		return lowCalories;
	}

	public static void main(String[] args) {
		createStreamFromIterator().forEach(System.out::println);
	}
}
