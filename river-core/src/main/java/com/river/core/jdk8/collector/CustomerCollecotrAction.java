package com.river.core.jdk8.collector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class CustomerCollecotrAction {
	public static void main(String[] args) {
		// Collector<T,A,R>
		Collector<String, List<String>, List<String>> collector = new ToListCollector<>();
		String[]arrs = new String[] {"Alex", "Wang", "lina", "Lambda", "Java8", "Stream"};
		List<String> result1 = Arrays.stream(arrs)
				.filter(i->i.length()>5)
				.collect(collector);
		List<String> result = Arrays.asList("Alex", "Wang", "lina", "Lambda", "Java8", "Stream")
				.parallelStream()
				.filter(s -> s.length() > 5)
				.collect(collector);

	}

}
