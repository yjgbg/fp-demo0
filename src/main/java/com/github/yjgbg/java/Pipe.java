package com.github.yjgbg.java;

import java.util.function.Function;

public class Pipe {

	public static void main(String[] args) {
		final Function<Integer, Boolean> isPrime = x -> x % 2 == 0; // 纯函数? 定义域? 值域? 单射? 满射?
		final Function<String, String> toUpperCase = String::toUpperCase; // 纯函数? 定义域? 值域? 单射? 满射?
		final Function<String, String[]> split = str -> str.split(","); // 纯函数? 定义域? 值域? 单射? 满射?
		final Function<Integer,Integer> add3 = x -> x + 3;
		final Function<Integer,Function<Integer,Integer>> add = a ->b -> a+b;
		final Function<Integer,Function<Integer,Integer>> divide = a ->b ->a / b;
		final var a = pipe(toUpperCase,split);
		final var predSeason = pipe(add.apply(3),divide.apply(4));
		final Function<Integer,Integer> pred = x ->(x + 3) / 4;
	}

	public static <A,B,C> Function<A,C> pipe(Function<A,B> f1,Function<B,C> f2) {
		return a -> f2.apply(f1.apply(a));
	}
}
