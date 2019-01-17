package com.river.core.jdk8.Lambda.dao;

public class ComplexApple {
private String color;
private long weight;
private String name;


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ComplexApple() {
	super();
}
public ComplexApple(String color, String name,long weight) {
	super();
	this.name = name;
	this.color = color;
	this.weight = weight;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public long getWeight() {
	return weight;
}
public void setWeight(long weight) {
	this.weight = weight;
}
@Override
public String toString() {
	return "ComplexApple [color=" + color + ", weight=" + weight + ", name=" + name + "]";
}


}
