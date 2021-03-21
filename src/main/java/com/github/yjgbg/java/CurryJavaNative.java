package com.github.yjgbg.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 柯里化的java化用法
// 因为在众多函数式语言中，是直接支持函数名后面跟括号传递参数来使用的 Function<A,B> func; var b = func(a);
// 而java并不支持: Function<A,B> func;var b = func.apply(a)
//这导致柯里化的一些常用法在java中显得比较啰嗦
// let y = add.apply(1).apply(2)
// let x = add.apply(1)
// let y = x 2
public class CurryJavaNative {
	public static void main(String[] args) {
		final var list0 = Stream.of(args).map(x -> x+"suffix").collect(Collectors.toList());
		final Function1<String, Function1<String,String>> add = a -> b -> a+b;
		//final var list1 = Stream.of(add("suffix")).collect(Collectors.toList()); // java中这种写法不合法
		final var list1 = Stream.of(args).map(add("suffix")).collect(Collectors.toList()); // java中这种写法不合法
	}

	public static Function<String,String> add(String a0) {
		return a1 -> a0+a1;
	}
}
