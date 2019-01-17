package com.river.core.jdk8.stream;

import java.util.Random;
import java.util.function.Supplier;

import com.river.core.jdk8.Lambda.dao.Obj;
/**
 * 自定义一个ObjSupplier，每次调用产生一个Obj
 * @author riverplant
 *
 */
public class ObjSupplier implements Supplier<Obj> {
    private int index = 0;
    private Random random = new Random(System.currentTimeMillis());//给random一个因子
	@Override
	public Obj get() {
		index = random.nextInt(100);//100之内的随机数
		// TODO Auto-generated method stub
		return new Obj(index, "name:"+index);
	}

}
