package com.river.core.jdk8.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * @author riverplant
 *
 */
public class StreamMatch {
public static void main(String[] args) {
	Stream<Integer>stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	//Stream.allMatch(Predicate<? super Integer> predicate)
	boolean matched = stream.allMatch(i->i>0);//stream总所有元素>0
	assert matched :"some elements not mached";//断言
	stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});//必须重现创建一次，因为流之前已经关闭
	 matched = stream.anyMatch(i->i>6);//stream中有一个元素>6
}
}
