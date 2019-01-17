package com.river.core.jdk8.Lambda.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.river.core.jdk8.Lambda.dao.Apple;

/**
 * Predicate boolean test(T t); Consumer accept(T t); Function<T,R> R apply(T
 * t); Supplier<T> T get();
 * 
 * @author riverplant
 *
 */
public class LambdaUsage {
	/**
	 * predicateFilter
	 * 
	 * @param source
	 * @param predicate
	 * @return
	 */
	private static List<Apple> predicateFilter(List<Apple> source, Predicate<Apple> predicate) {
		List<Apple> apples = new ArrayList<Apple>();
		for (Apple a : source) {
			if (predicate.test(a))
				apples.add(a);
		}
		return apples;
	}

	/**
	 * LongpredicateFilter
	 * 
	 * @param source
	 * @param predicate
	 * @return
	 */
	private static List<Apple> LongpredicateFilter(List<Apple> source, LongPredicate predicate) {
		List<Apple> apples = new ArrayList<Apple>();
		for (Apple a : source) {
			if (predicate.test(a.getWeight()))
				apples.add(a);
		}
		return apples;
	}

	private static List<Apple> BidicateFilter(List<Apple> source, BiPredicate<String, Long> predicate) {
		List<Apple> apples = new ArrayList<Apple>();
		for (Apple a : source) {
			if (predicate.test(a.getColor(), a.getWeight()))
				apples.add(a);
		}
		return apples;
	}

	private static void FilterByCustomer(List<Apple> source, Consumer<Apple> consumer) {

		for (Apple a : source) {
			consumer.accept(a);
		}
	}

	private static void FilterByBiCustomer(List<Apple> source, BiConsumer<String, Long> consumer) {

		for (Apple a : source) {
			consumer.accept(a.getColor(), a.getWeight());
		}
	}

	private static List<Apple> FilterByFunction(List<Apple> source, Function<Apple, Apple> function) {
		List<Apple> apples = new ArrayList<Apple>();
		for (Apple a : source) {
//			if (function.apply(a) != null)
//				apples.add(a);
			apples.add(function.apply(a));
		}

		return apples;
	}

	public static void main(String[] args) {
		// Runnable r1 = () -> System.out.println("Hello");
		// Runnable r2 = new Runnable() {
		// @Override
		// public void run() {
		// System.out.println("Hello");
		// }
		// };// r2
		// process(r1);
		// process(r2);
		// process(() -> System.out.println("Hello"));
		List<Apple> apples = Arrays.asList(new Apple("green", 120), new Apple("green", 120));
		List<Apple> greenList = predicateFilter(apples, a -> a.getColor().equals("green"));
		List<Apple> weightList = LongpredicateFilter(apples, w -> w > 100);
		List<Apple> BiList = BidicateFilter(apples, (c, w) -> c.equals("green") && w > 100);
		FilterByCustomer(apples, a -> System.out.println(a));
		FilterByBiCustomer(apples, (c, w) -> System.out.println(c + w));
//		List<Apple> FunctionList = FilterByFunction(apples, c -> {
//			if (c.getColor().equals("green")) {
//				return c;
//			} else {
//				return null;
//			}
//		});
		List<Apple> FunctionList = FilterByFunction(apples,c->{
			long weight = c.getWeight();
			c.setWeight(weight*2);
			return c;
		});
		IntFunction<Double> f = (i)->i*100.0d;
		double intFunction =f.apply(10);
		
		Supplier<String>s = String::new;
		
		System.out.println(s.get().getClass());
		
		Apple apple =  createApple(()->new Apple("red",200));
	}
	
	private static Apple createApple(Supplier<Apple> supplier){
		return supplier.get();
	}

	private static void process(Runnable r) {
		r.run();
	}
}
