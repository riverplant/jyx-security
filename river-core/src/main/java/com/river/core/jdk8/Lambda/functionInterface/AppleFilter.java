package com.river.core.jdk8.Lambda.functionInterface;

import com.river.core.jdk8.Lambda.dao.Apple;

@FunctionalInterface
public interface AppleFilter {

	boolean filter(Apple apple);
}
