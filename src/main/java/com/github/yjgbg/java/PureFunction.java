package com.github.yjgbg.java;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.control.Either;

import java.math.BigDecimal;

// 数学中的函数在编程语言中称为纯函数，指一种映射，代表从定义域(参数类型)到值域(返回值类型)的映射
// 不同数值之间的特殊关系：每一个输入值返回且只返回一个输出值。
// 判断纯函数的方式: 相同的输入，永远会得到相同的输出，而且没有任何可观察的副作用。
// 副作用是什么？
// 既然是纯函数(数学中的函数)，那么便可以讨论一些数学中的性质，比如是否单射，是否满射
// 如果既是单射，又是满射， 那么称之为双射，可以认为定义域和值域是等势的
// 单射： 指将不同的变量映射到不同的值的函数。
// 满射: 对值域（陪域）中任意元素，都存在至少一个定义域中的元素与之对应
// JDK的基本类型中，哪些数据类型之间是等势的?
// Long和long等势(其实就是size相等)吗？
public class PureFunction {
	public static void main(String[] args) {
		final Function1<Integer, Boolean> isPrime = x -> x % 2 == 0; // 纯函数? 定义域? 值域? 单射? 满射?
		final Function1<String, String> toUpperCase = String::toUpperCase; // 纯函数? 定义域? 值域? 单射? 满射?
		final Function1<String, String[]> split = str -> str.split(","); // 纯函数? 定义域? 值域? 单射? 满射?
		final Function0<Double> rdm = Math::random; // 纯函数? 定义域? 值域? 单射? 满射?
		final Function1<String, Integer> parseInt = Integer::parseInt; // 纯函数?  定义域? 值域? 单射? 满射?
		final Function1<DTO,DTO> processDTO = dto -> { // 纯函数? 定义域? 值域? 单射? 满射?
			if (dto.getName() != null) dto.setStatus(true);
			return dto;
		};
		final Function1<Double, Long> double2Long = Double::doubleToRawLongBits; // 纯函数? 定义域? 值域? 单射? 满射?
		final Function1<Long, Double> long2Double = Double::longBitsToDouble; // 纯函数? 定义域? 值域? 单射? 满射?
		//上面两个函数为双射，也就是说Long和Double是等势的，底层原因：都是64位二进制数，所以它们能包含的信息量是相等的，同理：int和float是等势的
		final Function1<BigDecimal, BigDecimal> doub = x -> x.multiply(BigDecimal.valueOf(2)); // 纯函数? 定义域? 值域? 单射? 满射?

	}

	public static Either<NumberFormatException, Integer> parseInt(String string) {
		try {
			return Either.right(Integer.parseInt(string));
		} catch (NumberFormatException e) {
			return Either.left(e);
		}
	}

	static class DTO {
		private int id;
		private String name;
		private boolean status;

		public String getName() {
			return name;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public boolean getStatus() {
			return status;
		}
	}
}