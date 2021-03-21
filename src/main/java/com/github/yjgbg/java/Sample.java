package com.github.yjgbg.java;

import java.util.function.Function;

import static com.github.yjgbg.java.F.*;

public class Sample {
	public static void main(String[] args) {
		final Function<String,String> f = String::toUpperCase;
		final Function<String,Character> firstChar = s -> s.charAt(0);
	  final var firstCharUpperCase = pipe(f,firstChar);
	}
}
