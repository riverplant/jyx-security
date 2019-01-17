package com.river.core.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 
 * @author riverplant
 *
 */
public class StreamFilter {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,6,7,7,7,8);
		//filter(Predicate<? super Integer> predicate)
		//skip(2):跳过前两个元素
		List<Integer> result = list.stream().filter(i->i%2==0).skip(2).limit(5).distinct().collect(Collectors.toList());
		System.out.println(result);
	}
}
