package com.river.core.jdk8.stream;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FindStream {
public static void main(String[] args) {
	Stream<Integer>stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	Optional<Integer> optional1 = stream.filter(i->i%2==0).findAny();
	System.out.println(optional1.get());
	stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	Optional<Integer> optional2 = stream.filter(i->i%2==0).findFirst();
	/**
	 * 
	 */
	stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7});
	Optional<Integer> optional3 = stream.filter(i->i>10).findAny();
	optional3.ifPresent(System.out::println);
	System.out.println(optional3.orElse(-1));
	
	int result = find(new Integer[] {1,2,3,4,5,6,7},-1,i->i>100);
	System.out.println(result);
}
/**
 * 
 * @param values
 * @param defaultValue
 * @param predicate
 * @return
 */
private static int find(Integer[] values ,int defaultValue,Predicate<Integer>predicate) {
	for(int i:values) {
		if(predicate.test(i)) {
			return i;
		}
	}
	return defaultValue;
}
}
