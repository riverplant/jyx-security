package com.river.core.jdk8.Lambda.expression;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.river.core.jdk8.Lambda.dao.Apple;

public class LambdaExpression {

	public static void main(String[] args) {
		Comparator<Apple> byColor = new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getColor().compareTo(o2.getColor());
			}
		};		
		List<Apple>list = Collections.emptyList();
		list.sort(byColor);		
		//lambda:当不使用{}时不需要写return
		Comparator<Apple> byColor2 = (o1,o2)->o1.getColor().compareTo(o2.getColor());
		list.sort(byColor2);
	}
}
