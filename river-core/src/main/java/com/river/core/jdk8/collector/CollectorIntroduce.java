package com.river.core.jdk8.collector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.river.core.jdk8.Lambda.dao.Apple;

public class CollectorIntroduce {

	public static void main(String[] args) {
		List<Apple> apples = Arrays.asList(new Apple("green", 120)
				, new Apple("green", 120)
				, new Apple("yellow", 170)
				, new Apple("yellow", 150));
		
		List<Apple> greens = apples.stream().filter(i->i.getColor().equals("green")).collect(Collectors.toList());
		Optional.ofNullable(greens).ifPresent(System.out::println);	
	}
	
	private static Map<String,List<Apple>>groupByNormal(List<Apple>apples){
		Map<String,List<Apple>> map = new HashMap<String,List<Apple>>();
		for(Apple apple:apples) {
			List<Apple>list = map.get(apple.getColor());
			if(null==list) {
				list = new ArrayList<>();
				map.put(apple.getColor(), list);
			}
			list.add(apple);//不论是否是第一个，都要加入新的苹果
		}
		return map;
	}
	
	private static Map<String,List<Apple>>groupByFunction(List<Apple>apples){
		Map<String,List<Apple>> map = new HashMap<String,List<Apple>>();
		apples.stream().forEach(a->{
			List<Apple> colorList =	Optional.ofNullable(map.get(a.getColor())).orElseGet(()->{//如果得到的list为空
				List<Apple> list = new ArrayList();
				map.put(a.getColor(), list);
				return list;
			});
			colorList.add(a);
		});
		return map;
	}
	/**
	 * 
	 * @param apples
	 * @return
	 */
	private static Map<String,List<Apple>>groupByCollector(List<Apple>apples){
		return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
	}
	
}
