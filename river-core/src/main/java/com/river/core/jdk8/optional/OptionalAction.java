package com.river.core.jdk8.optional;
import java.util.Optional;
import com.river.core.jdk8.Lambda.dao.Person;
/**
 * Optional实战demo
 * @author riverplant
 *
 */
public class OptionalAction {
public static void main(String[] args) {
	
}
/**
 * map中获取的返回值自动被Optional包装,即返回值 -> Optional<返回值>
   flatMap中返回值保持不变,但必须是Optional类型,即Optional<返回值> -> Optional<返回值>
 * @param person
 * @return
 */
private static String getInsuranceNameByOptional(Person person) {
	 String insuranceName = Optional.ofNullable(person)
	.map(Person::getCar)//返回一个 Optional<Car>
	.map(i->i.getInsurance().getName()).orElse("unkown");
	return insuranceName;	
}
}
