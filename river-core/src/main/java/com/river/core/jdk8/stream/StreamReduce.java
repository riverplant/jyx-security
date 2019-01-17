package com.river.core.jdk8.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class StreamReduce {
public static void main(String[] args) {
	Stream<Integer>stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	//Stream.reduce(Integer identity, BinaryOperator<Integer> accumulator)
	//public interface BinaryOperator<T> extends BiFunction<T,T,T>
	stream.reduce(0, (i,j)->i+j);//将数组中的所有数字加起来
	//stream.reduce(0, Integer::sum);//第二个参数BinaryOperator<T>是一个Function,所以可以直接写
	stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	 stream.reduce((i,j)->i+j).ifPresent(System.out::println);
	 stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	 stream.reduce((i,j)->{
		 return i>j?i:j;
	 }).ifPresent(System.out::println);
	 }
	//option.get();
}

