package com.river.core.jdk8.Lambda.functionInterface;
@FunctionalInterface
public interface ComplextFunction<T,U,N,R> {
/**
 * 接收三个参数的自定义functional interface	
 * @param t
 * @param u
 * @param n
 * @return
 */
R Apply(T t,U u, N n);
}
