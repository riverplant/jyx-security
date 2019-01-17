package com.river.core.jdk8.optional;

import java.util.Optional;

import com.river.core.jdk8.Lambda.dao.Insurance;

public class OptionalUsage {
public static void main(String[] args) {
	
	 Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();
	 insuranceOptional.get();
	 
	 Optional<Insurance> insuranceOptional2 = Optional.of(new Insurance());
	 insuranceOptional2.get();
	 /**
	  * Optional.ofNullable(T value); 当value为null就相当于调用
	  * Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();
	  * 当value为new Insurance()就相当于调用Optional.of(new Insurance());
	  */
	 Optional<Insurance> insuranceOptional3 = Optional.ofNullable(new Insurance());
	 //Optional.orElseGet(Supplier<? extends Insurance> other)
	 insuranceOptional3.orElseGet(Insurance::new);
	 insuranceOptional3.orElse(new Insurance());
	 insuranceOptional3.orElseThrow(RuntimeException::new);
	 insuranceOptional3.orElseThrow(()->new RuntimeException("Not have reference"));
	 Insurance insurance = insuranceOptional3.filter(i->i.getName()==null).get();
	 Optional<String> nameOptional = insuranceOptional3.map(i->i.getName());
	 System.out.println(nameOptional.orElse("empty Value"));
}

private static String getInsuranceName(Insurance insurance) {
	if(null==insurance)return "unkown";
	return insurance.getName();
}
private static String getInsuranceNameByOptional(Insurance insurance) {
	return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unkown");	
}
}
