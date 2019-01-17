package com.river.core.MultiThreads;

import java.util.Arrays;
import java.util.List;

/**
 * 用lambda表达式做并行运算
 * @author riverplant
 *
 */
public class LambdaDemo {
	
	public static void main(String[] args) {
		List<Integer>values = Arrays.asList(10,20,30,40);//构建了一个长度为4的集合
		int result = new LambdaDemo().add(values);
		System.out.println("结果等于:"+result);
	}

	public int add(List<Integer>values) {
		values.parallelStream().forEachOrdered(System.out::println);//并行打印集合中的值,通过forEachOrdered来排序
		values.stream().forEach(System.out::println);//串行打印集合中的值
		return values
				.parallelStream()//并行,相当于一个interator
				.mapToInt(a -> a)//将集合中每一个元素取出转成int类型,
				.sum();//求和
	}
	
	public int addString(List<String>values) {
		values.parallelStream().forEachOrdered(System.out::println);//并行打印集合中的值,通过forEachOrdered来排序
		values.stream().forEach(System.out::println);//串行打印集合中的值
		return values
				.parallelStream()//并行,相当于一个interator
				.mapToInt(a -> Integer.parseInt(a))//将集合中每一个元素取出转成int类型,
				.sum();//求和
	}
}
