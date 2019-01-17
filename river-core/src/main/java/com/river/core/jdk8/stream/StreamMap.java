package com.river.core.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author riverplant
 *
 */
public class StreamMap {
	public static void main(String[] args) {
		//List<Integer> list = Arrays.asList(1,2,3,4,5,6,6,7,7,7,8);
		//map(Function<? super Integer, ? extends Integer> mapper)
		//list中的元素都增大为2倍
		//List<Integer> result = list.stream().map(a->a*2).collect(Collectors.toList());
		//System.out.println(result);
		//flatmap:(扁平化):去掉String数组中重复的chart
		String[] words = {"hello","worlds"};
		Stream<String[]> stream = Arrays.stream(words).map(w->w.split(""));//Stream<String[]>
		//有两个字符串数组{h,e,l,l,o},{w,o,r,l,d,s}
		//System.out.println(stream.collect(Collectors.toList()).get(0)[0]);
		//扁平化后变成h,e,l,l,o,w,o,r,l,d
		Stream<String> stringstream = stream.flatMap(Arrays::stream);//将两个字符创String合并成一个!!!!
		stringstream.distinct().forEach(System.out::print);
	}
}
