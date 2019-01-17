package com.river.core.jdk8.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import com.river.core.enums.Type;
import com.river.core.jdk8.Lambda.dao.Dish;

public class CollectorsAction {
	// 创建menu
	private final static List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Type.MEAT),
			new Dish("beef", false, 700, Type.MEAT), 
			new Dish("chicken", false, 400, Type.MEAT),
			new Dish("french frits", true, 530, Type.OTHER), 
			new Dish("rice", true, 350, Type.OTHER),
			new Dish("pizza", true, 550, Type.OTHER));	
	/**
	 * Collectors.averagingDouble获得一个list中所有元素的平均数
	 */
	private static void testAveragingDouble() {
		//Collectors.averagingDouble转换后转成一个double类型
		Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
		                    .ifPresent(System.out::println);;
	}
	/**
	 * Collectors.averagingInt:转成一个int类型
	 */
	private static void testAveragingInt() {
		//Collectors.averagingDouble转换后转成一个double类型
		Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
		                    .ifPresent(System.out::println);;
	}
	
	/**
	 * Collectors.averagingInt:转成一个long类型
	 */
	private static void testAveragingLong() {
		//Collectors.averagingDouble转换后转成一个double类型
		Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
		                    .ifPresent(System.out::println);;
	}
	
	/**
	 * testCollectingAndThen：两步处理
	 */
	private static void testCollectingAndThen() {
		/**collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher)
		 * collectingAndThen(Collectors.averagingInt(Dish::getCalories), a->"this value is "+a))
		 * Collectors.averagingInt(Dish::getCalories)作为第一个结果downstream传给第二个表达式
		 * a->"this value is "+a)：a为downstream传入给finisher，返回一个"this value is "+a
		 */
		Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a->"this value is "+a)))
		                    .ifPresent(System.out::println);
		//返回一个list且不可以修改
		List<Dish> list = menu.stream().filter(i->i.getType().equals(Type.MEAT))
		.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));//这里导入java.util.Collections		
		//因为中间可以修改该list所以需要设置为不可修改
		list.add(new Dish("other",false,50,Type.OTHER));
		
	}
	/**
	 * 算出list的个数
	 */
	private static void testCounting() {
		Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
	}
	/**
	 * 根据Type进行分类
	 */
	private static void testgroupingByFunction() {
		Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);	
	}
	/**
	 * 根据Type进行分类,然后统计个数
	 * 根据Type进行分类,然后统计每一类calorie平均值
	 */
	private static void testgroupingByFunctionCouting() {
		/**
		 * java.util.stream.Collectors
		 * .groupingBy(Function<? super Dish, ? extends Type> classifier, Collector<? super Dish, Object, Long> downstream)
		 */
		Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting())))
		.ifPresent(System.out::println);
		Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.averagingInt(Dish::getCalories))))
		.ifPresent(System.out::println);
	}
	
	private static void testgroupingByFunctionAndSupplierAndCollector() {
		/**
		 * java.util.stream.Collectors.groupingBy
		 * (Function<? super Dish, ? extends Type> classifier, 
		 * Supplier<TreeMap<Type, Double>> mapFactory, [get()]-->TreeMap::new//通过这个参数来实现map转换，可以转成自己定义的map
		 * Collector<? super Dish, Object, Double> downstream)
		 */
		Map<Type,Double> map = menu.stream().collect(Collectors
				.groupingBy(Dish::getType,TreeMap::new,Collectors.averagingInt(Dish::getCalories)));
		
	}
	/**
	 * 获得list的统计概要，包括平均值、最大值、最小值、、、
	 */
	private static void testSummerizingInt() {
		//
		IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
	    Optional.ofNullable(result).ifPresent(System.out::println);
	}
}
