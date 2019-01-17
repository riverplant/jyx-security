package com.river.core.jdk8.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 继承Collector接口 public interface Collector<T, A, R>
 * 
 * T:传入的Stream中的元素类型 A:累加器:容器:Supplier类型 R:返回值Function
 * 
 * @author riverplant
 *
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	private void log(final String log) {
		System.out.println(Thread.currentThread().getName()+"-"+log);
	}
	@Override // get()创建容器
	public Supplier<List<T>> supplier() {
		log("supplier");
		// TODO Auto-generated method stub
		return ArrayList::new;
	}

	/**
	 * 将容器中的值挨个消费,两个入参:1.container,2.容器中的元素
	 * 
	 * @FunctionalInterface public interface BiConsumer<T, U>
	 */
	@Override // accumulator去执行accept(accumulator,next)
	public BiConsumer<List<T>, T> accumulator() {
		log("accumulator");
		// TODO Auto-generated method stub
		return List::add;
	}

	/**
	 * accumulator: BinaryOperator<A> combiner();//extends BiFunction<T,U,R> ->R
	 * apply(T t, U u); extends BiFunction<T,U,R> ->R apply(T t, U u);
	 * combiner:接收两个部分的结果，并且进行合并
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		log("combiner");
		return (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		};
	}

	// R result = collector.finisher().apply(accumulator);
	@Override
	public Function<List<T>, List<T>> finisher() {
		log("finisher");
		/**
		 * static <T> Function<T, T> identity() { return t -> t; }
		 * 返回输入参数
		 */
		return Function.identity();
	}

	/**
	 * 特征值 enum Characteristics { /** Indicates that this collector is
	 * <em>concurrent</em>, meaning that the result container can support the
	 * accumulator function being called concurrently with the same result container
	 * from multiple threads.
	 *
	 * <p>
	 * If a {@code CONCURRENT} collector is not also {@code UNORDERED}, then it
	 * should only be evaluated concurrently if applied to an unordered data source.
	 * 
	 * CONCURRENT,
	 */

	/**
	 * Indicates that the collection operation does not commit to preserving the
	 * encounter order of input elements. (This might be true if the result
	 * container has no intrinsic order, such as a {@link Set}.)
	 * 
	 * UNORDERED,
	 */

	/**
	 * Indicates that the finisher function is the identity function and can be
	 * elided. If set, it must be the case that an unchecked cast from A to R will
	 * succeed. 
	 * 
	 * IDENTITY_FINISH
	 */
	@Override
	public Set<Characteristics> characteristics() {
		log("characteristics");
		// <Characteristics> Set<Characteristics>
		// java.util.Collections.unmodifiableSet(Set<? extends Characteristics> s)
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
	}

}
