package com.river.core.jdk8.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.util.Comparators;

import com.river.core.enums.Type;
import com.river.core.jdk8.Lambda.dao.Dish;

public class CollectorsAction3 {
	// 创建menu
	private final static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
			new Dish("beef", false, 700, Type.MEAT), new Dish("chicken", false, 400, Type.MEAT),
			new Dish("french frits", true, 530, Type.OTHER), new Dish("rice", true, 350, Type.OTHER),
			new Dish("pizza", true, 550, Type.OTHER));

	/**
	 * <Dish> Collector<Dish, ?, Map<Boolean, List<Dish>>>
	 * java.util.stream.Collectors.partitioningBy(Predicate<? super Dish> predicate)
	 */
	private static void testPartitionByPredicate() {
		Map<Boolean, List<Dish>> list = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
		Optional.ofNullable(list).ifPresent(System.out::println);
	}

	/**
	 * 条件成立返回true
	 */
	private static void testPartitionByPredicateAndCollector() {
		menu.stream()
				.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));

	}

	/**
	 * 聚会，要拿到calorie最大的 BinaryOperator.maxBy:返回一个 BinaryOperator<Dish>
	 */
	private static void testReducingBinaryOperator() {
		menu.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));

	}

	/**
	 * 获得calori的总和 返回一个T <Integer> Collector<Integer, ?, Integer>
	 * java.util.stream.Collectors.reducing(Integer identity,
	 * BinaryOperator<Integer> op)
	 */
	private static void testReducingBinaryOperatorAndIdentify() {
		Integer result = menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));

	}

	/**
	 * <Dish, Integer> Collector<Dish, ?, Integer>
	 * java.util.stream.Collectors.reducing( Integer identity, Function<? super
	 * Dish,? extends Integer> mapper, BinaryOperator<Integer> op)
	 * 该方法直接使用collect,reducing中的第二个参数用来map
	 */
	private static void testReducingBinaryOperatorAndIdentifyAndFunction() {
		menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));

	}

	/**
	 * 获得summarizingStatistic
	 */
	private static void testSummarizingDouble() {
		menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories));
		menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		menu.stream().collect(Collectors.summarizingLong(Dish::getCalories));
	}

	/**
	 * 得到list中calorie的值的总和，转成double <Dish> Collector<Dish, ?, Double>
	 * java.util.stream.Collectors.summingDouble(ToDoubleFunction<? super Dish>
	 * mapper)
	 */
	private static void testSummingDouble() {
		menu.stream().collect(Collectors.summingDouble(Dish::getCalories));
		// 方法2
		menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum();

	}

	/**
	 * <Dish> Collector<Dish, ?, Long>
	 * java.util.stream.Collectors.summingLong(ToLongFunction<? super Dish> mapper)
	 */
	private static void testSummingLong() {
		menu.stream().collect(Collectors.summingLong(Dish::getCalories));
		// 方法2
		menu.stream().map(Dish::getCalories).mapToLong(Integer::longValue).sum();
	}

	/**
	 * 将Stream的结果过滤后放入LinkedList中 <Dish, LinkedList<Dish>> Collector<Dish, ?,
	 * LinkedList<Dish>>
	 * java.util.stream.Collectors.toCollection(Supplier<LinkedList<Dish>>
	 * collectionFactory)
	 */
	private static void testToCollectioin() {
		menu.stream().filter(i -> i.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new));

	}

	/**
	 * 将名字和calorie的值拿出来作为key 和 value 放入ConcurrentMap中
	 * java.util.stream.Collectors.toConcurrentMap(Function<? super Dish, ? extends
	 * String> keyMapper, Function<? super Dish, ? extends Integer> valueMapper)
	 */
	private static void testToConcurrentMap() {
		menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories));
	}

	/**
	 * 相同的内容进行合并
	 */
	private static void testToConcurrentMapAndMergeWithBinaryOperator() {
		// Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType,
		// Dish::getCalories,(a,b)->a+b)))
		// .ifPresent(System.out::println);
		/**
		 * V->1L:一个类型给一个值1作为个数,表示一个key值Dish::getType对应一个value:1,
		 * (a,b)->a+b:将相同的key值对应的value值加起来
		 */
		Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, V -> 1, (a, b) -> a + b)))
				.ifPresent(System.out::println);
	}

	/**
	 * 将数据结构转成ConcurrentSkipListMap
	 * <Dish, Type, Integer, ConcurrentSkipListMap<Type, Integer>> Collector<Dish,
	 * ?, ConcurrentSkipListMap<Type, Integer>>
	 * java.util.stream.Collectors.toConcurrentMap(Function<? super Dish, ? extends
	 * Type> keyMapper, Function<? super Dish, ? extends Integer> valueMapper,
	 * BinaryOperator<Integer> mergeFunction, Supplier<ConcurrentSkipListMap<Type,
	 * Integer>> mapSupplier)
	 */
	private static void testToConcurrentMapAndMergeWithBinaryOperatorAndSupplier() {
		Optional.ofNullable(menu.stream().collect(
				Collectors.toConcurrentMap(Dish::getType, V -> 1, (a, b) -> a + b, ConcurrentSkipListMap::new)))
				.ifPresent(System.out::println);
	}
	/**
	 * 为Collectors.toMap返回的map值添加同步线程安全
	 * Collections::synchronizedMap
	 */
	private static void testToMap() {
		menu.stream().collect(Collectors.collectingAndThen(Collectors.toMap(Dish::getName, Dish::getCalories),Collections::synchronizedMap ));
	}
	public static void main(String[] args) {
		testToConcurrentMapAndMergeWithBinaryOperator();
	}

}
