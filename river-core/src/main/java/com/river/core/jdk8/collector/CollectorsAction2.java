package com.river.core.jdk8.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.util.Comparators;

import com.river.core.enums.Type;
import com.river.core.jdk8.Lambda.dao.Dish;

public class CollectorsAction2 {
	// 创建menu
	private final static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
			new Dish("beef", false, 700, Type.MEAT), new Dish("chicken", false, 400, Type.MEAT),
			new Dish("french frits", true, 530, Type.OTHER), new Dish("rice", true, 350, Type.OTHER),
			new Dish("pizza", true, 550, Type.OTHER));

	/**
	 * public interface ConcurrentMap<K, V> extends Map<K, V>
	 * ConcurrentHashMap:返回一个支持并发的HashMap，对list中的内容进行了分组
	 */
	private static void testGroupingByConcurrentWithFunction() {
		// Collectors.averagingDouble转换后转成一个double类型
		/**
		 * java.util.stream.Collectors.groupingByConcurrent(Function<? super Dish, ?
		 * extends Type> classifier) 返回一个ConcurrentMap<Type, List<Dish>>
		 * 将list中的内容按type分类
		 */
		ConcurrentMap<Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
		Optional.ofNullable(collect).ifPresent(System.out::println);
	}

	/**
	 * public interface ConcurrentMap<K, V> extends Map<K, V>
	 * ConcurrentHashMap:返回一个支持并发的HashMap，对list中的内容进行了分组
	 */
	private static void testGroupingByConcurrentWithFunctionAndCollector() {
		/**
		 * 带两个参数:function and Collector java.util.stream.Collectors
		 * .groupingByConcurrent(Function<? super Dish, ? extends Type> classifier,
		 * Collector<? super Dish, Object, Double> downstream) 返回一个ConcurrentMap<Type,
		 * Double>：在各种类型下有多少的calorie
		 */
		ConcurrentMap<Type, Double> collect = menu.stream()
				.collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));

	}

	private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
		/**
		 * <Dish, Type, Object, Double, ConcurrentSkipListMap<Type, Double>>
		 * Collector<Dish, ?, ConcurrentSkipListMap<Type, Double>>
		 * java.util.stream.Collectors.groupingByConcurrent(Function<? super Dish, ?
		 * extends Type> classifier, Supplier<ConcurrentSkipListMap<Type, Double>>
		 * mapFactory, Collector<? super Dish, Object, Double> downstream)
		 */
		ConcurrentMap<Type, Double> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType,
				ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
	}

	/**
	 * static Collector<CharaSequence,?,String> joining() 将list中的名字连接
	 */
	private static void testJoining() {
		menu.stream().map(Dish::getName).collect(Collectors.joining());
	}

	/**
	 * static Collector<CharaSequence,?,String> joining() 将list中的名字连接,用分隔符分隔
	 */
	private static void testJoiningWithDelimiter() {
		menu.stream().map(Dish::getName).collect(Collectors.joining("\\"));
	}

	/**不能直接joining,需要先map
	 * static Collector<CharaSequence,?,String> joining()
	 * 将list中的名字连接,用分隔符分隔,整体带上前缀和后缀
	 */
	private static void testJoiningWithDelimiterAndPrifixAndSuffix() {
		menu.stream().map(Dish::getName).collect(Collectors.joining("//", "prefix", "suffix"));
	}
	/**
	 * 通过Collectors.mapping可以直接joining
	 */
	private static void testMappingJoiningWithDelimiterAndPrifixAndSuffix() {
		menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining("//", "prefix", "suffix")));
	}
	/**
	 * 获得最大值
	 */
	private static void testMaxBy() {
		menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)))
		.ifPresent(System.out::println);
	}
	
	/**
	 * 获得最小值
	 */
	private static void testMinBy() {
		menu.stream().collect(Collectors.minBy(Comparator.comparing(Dish::getCalories)))
		.ifPresent(System.out::println);
	}
}
