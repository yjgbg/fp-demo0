package com.github.yjgbg.java;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class F {
	public static <A,B,C> Function<A,C> pipe(Function<A,B> f0,Function<B,C> f1) {
		return a -> f1.apply(f0.apply(a));
	}

	public static <A,B> Function<List<A>,List<B>> map(Function<A,B> f) {
		return aList -> aList.stream().map(f).collect(Collectors.toList());
	}

	public static <A> Function<List<A>,List<A>> filter(Function<A,Boolean> f) {
		return aList -> aList.stream().filter(f::apply).collect(Collectors.toList());
	}

	public static <A,B> B reduce(B initial,Function<B,Function<A,B>> combiner,List<A> list) {
		return list.stream().reduce(initial,(a,b) -> combiner.apply(a).apply(b),(__,___) -> null);
	}

	public static <A,B,C> Function<A,Function<B,C>> curry(BiFunction<A,B,C> function) {
		return a -> b -> function.apply(a,b);
	}

	public static <A,B,C> Function<B,Function<A,C>> reverse(Function<A,Function<B,C>> f) {
		return b -> a -> f.apply(a).apply(b);
	}
}
