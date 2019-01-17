package com.river.core.jdk8.stream;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * NumericStream
 * @author riverplant
 *
 */
public class NumericStream {
public static void main(String[] args) {
	
	Stream<Integer> stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7,8});
	//Stream.mapToInt(ToIntFunction<? super Integer> mapper)
	/**
	 * @FunctionalInterface
      public interface ToIntFunction<T> {
        int applyAsInt(T value);}//传入一个参数，返回一个int
	 */
	IntStream intstream = stream.mapToInt(i->i.intValue());
	
	intstream.filter(i->i>3).sum();//使用IntStream可以节省内存,当使用Stream的时候操作的是Obj
	Integer result = stream.filter(i->i.intValue()>3).reduce(0,Integer::sum);
	//int : 4个字节，32位
	
	stream = Arrays.stream(new Integer[] {1,2,3,4,5,6,7,8});
}
/**勾股定理
 * a*a + b*b = c*c
 * Math.sqrt(a*a+b*b)%1==0
 * 返回一个int[a,b,c]
 */
private static void tripDemo() {
	int a = 9;
	/**Returns a sequential ordered IntStream from startInclusive
	 * (inclusive) to endInclusive (inclusive) by an incremental step of 1.
	 */
	IntStream intStream = IntStream.rangeClosed(1, 100);
	 intStream.filter(b->Math.sqrt(a*a+b*b)%1==0)
	.boxed()//将IntStream封箱回Stream<int[]>来方便调用map方法返回一个int[]
	.map(b -> new int[]{a,b,(int)Math.sqrt(a*a+b*b)})//Stream<int[]>stream
	.forEach(r->System.out.println("a="+r[0]+"b="+r[1]+"c="+r[2]));
	 //====================================================================
	 IntStream.rangeClosed(1, 100).filter(b ->Math.sqrt(a*a+b*b)%1==0)
	 .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
	 .forEach(r->System.out.println("a="+r[0]+"b="+r[1]+"c="+r[2]));
}
}
