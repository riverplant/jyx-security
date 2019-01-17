package com.river.core.jdk8.MultiThreads;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 多线程
 * 
 * @author riverplant
 *
 */
public class ParalleProcessing {
	public static void main(String[] args) {
		mesureSumPerformance(ParalleProcessing::normalAdd,10000);//快
		mesureSumPerformance(ParalleProcessing::iterateStream,10000);
		//mesureSumPerformance(ParalleProcessing::iterateStreamParalle,10000);超级慢
		mesureSumPerformance(ParalleProcessing::iterateStreamParalle2,10000);
		mesureSumPerformance(ParalleProcessing::LongStreamParalle,10000);//并行最快
	}

	/**
	 * 该函数用于测试函数循环运行相同次数的速度
	 * @param adder
	 * @param limit
	 * @return
	 */
	private static long mesureSumPerformance(Function<Long, Long> adder, long limit) {
		long faster = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long startTimstamp = System.currentTimeMillis();
			long result = adder.apply(limit);
			long duration = System.currentTimeMillis() - startTimstamp;
			System.out.println("sum="+result);
			if(duration<faster) faster = duration;
		}

		return faster;
	}
	/**Stream.iterate的parallel性能非常差,和LinkedList一样
	 * Stream的并行操作
	 * @param limit
	 * @return
	 */
	private static long iterateStreamParalle(long limit) {
		return Stream.iterate(1L, i->i+1).parallel().limit(limit).reduce(0L,Long::sum);
	}
	/**
	 * mapToLong(Long::longValue):对object进行拆箱
	 * @param limit
	 * @return
	 */
	private static long iterateStreamParalle2(long limit) {
		return Stream.iterate(1L, i->i+1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L,Long::sum);
	}
	/**LongStream.rangeClosed使用parallel性能非常好
	 * 使用LongStream来实现
	 * @param limit
	 * @return
	 */
	private static long LongStreamParalle(long limit) {
		return LongStream.rangeClosed(1, limit).parallel().sum();
	}

	/**
	 * 通过Stream获得叠加的结果
	 * 
	 * @param limit
	 * @return
	 */
	private static long iterateStream(long limit) {
		/**
		 * Stream<T> java.util.stream.Stream.iterate(T seed, UnaryOperator<T> f) Type
		 * Parameters:<T> the type of stream elements seed: the initial element f: a
		 * function to be applied to to the previous element to produce a new element
		 * Returns:a new sequential Stream
		 */
		return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
	}

	/**
	 * 非Stream的写法
	 * 
	 * @param limit
	 * @return
	 */
	private static long normalAdd(long limit) {
		long result = 0L;
		for (long i = 0; i < limit; i++) {
			result += i;
		}
		return result;
	}
}
