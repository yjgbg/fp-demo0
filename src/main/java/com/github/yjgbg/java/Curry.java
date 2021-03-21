package com.github.yjgbg.java;

import io.vavr.Function1;
import io.vavr.Function2;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Curry {
	public static <A,B,C> Function<A, Function<B,C>> curry(BiFunction<A,B,C> function) {
		return a -> b -> function.apply(a,b);
	}


	public static void main(String[] args) {
		final var charAt = curry(CharSequence::charAt);
		final Function2<CharSequence,CharSequence,Boolean> equals = CharSequence::equals;
		final var curried = equals.curried();
		final var eqsALICE = curried.apply("ALICE");
		System.out.println(eqsALICE.apply("ALICE"));
		final Function1<CharSequence, Function1<CharSequence,Boolean>> cmp = s1 -> s1::equals;
	}

}
