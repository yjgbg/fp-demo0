package com.github.yjgbg.java;

import java.util.HashMap;
import java.util.Map;

public class TestFib {
	public static void main(String[] args) {
		final var time0 = System.currentTimeMillis();
		fib0(46);
		final var time1 = System.currentTimeMillis();
		fib1(46);
		final var time2 = System.currentTimeMillis();
		System.out.println("fib0耗时:"+(time1-time0));
		System.out.println("fib1耗时:"+(time2-time1));
		// fib0与fib1性能差距巨大
	}


	// 关于纯函数的缓存
	// 没缓存
	public static int fib1(int i) {
		return i ==0 ? 0 : i == 1 ? 1:fib1(i-1)+fib1(i-2);
	}

	//有缓存
	private static final Map<Integer,Integer> FIB_CACHE = new HashMap<>();
	public static int fib0(int i) {
		final var res0 = FIB_CACHE.get(i);
		if (res0!=null) return res0;
		final var res1 = i ==0 ? 0 : i == 1 ? 1:fib0(i-1)+fib0(i-2);
		FIB_CACHE.put(i,res1);
		return res1;
	}
}
