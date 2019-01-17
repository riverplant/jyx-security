package com.river.core.jdk8.Lambda.filter;

import java.util.ArrayList;
import java.util.List;

import com.river.core.jdk8.Lambda.dao.Apple;
import com.river.core.jdk8.Lambda.functionInterface.AppleFilter;
/**
 * assert :断言   [assert list.size()==2 : return false] 当断言内容为假会报错
 * @author riverplant
 *
 */
public class FilterApple {

	public static List<Apple> findGreenApple(List<Apple>appels){
		List<Apple> list = new ArrayList<>();
		for(Apple apple :appels ) {
			if("green".equalsIgnoreCase(apple.getColor())) {
				list.add(apple);
			}
		}
		return list;
	}
	
	public static List<Apple> findApple(List<Apple>appels,String color){
		List<Apple> list = new ArrayList<>();
		for(Apple apple :appels ) {
			if(color.equalsIgnoreCase(apple.getColor())) {
				list.add(apple);
			}
		}
		return list;
	}
	/**
	 * 策略模式
	 * @param appels
	 * @param appleFilter
	 * @return
	 * 
	 * findApple(list,new GreenAnd50WeightFilter())
	 */
	public static List<Apple> findApple(List<Apple>appels,AppleFilter appleFilter){
		List<Apple> list = new ArrayList<>();
		for(Apple apple :appels ) {
			if(appleFilter.equals(apple)) {
				list.add(apple);
			}
		}
		return list;
	}
	/**
	 * 通过集成接口来实现业务需求
	 * @author riverplant
	 *
	 */
	public static class GreenAnd50WeightFilter implements AppleFilter{

		@Override
		public boolean filter(Apple apple) {
			
			return (apple.getColor().equals("green")&&apple.getWeight()>=150);
		}	
	}
	/**
	 *anonyme
	 * @param args
	 */
	public static void main(String[] args) {
		List<Apple> list = new ArrayList<>();
		//通过匿名内部类来做
		List<Apple> yellowList = findApple(list,new AppleFilter() {
			@Override
			public boolean filter(Apple apple) {
				// TODO Auto-generated method stub
				return (apple.getColor().equals("yellow"));
			};
		});//
		//lambda
		List<Apple> yellowList2 = findApple(list, apple ->{
			return (apple.getColor().equals("yellow"));
		} );
	}
}
