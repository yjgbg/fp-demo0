## 一等公民

脱离了类，对象而存在的函数，java并不支持

## 纯函数

- 没有副作用
- 相同的输入，永远会得到相同的输出

例子：

// 什么是函数式编程

// 函数具体是指什么 ？ 纯函数，数学中的函数

// 函数指数学中的函数

// 数学中的函数与编程语言中的函数有什么区别

数学函数: f x = x +1

数学函数: f x = 2 x

编程语言中的函数

function example(var x) {

​		x.abc = "asd"

}

// https://www.zhihu.com/question/20385250 数学中的函数没有副作用，不依赖外部参数，结果是确定的,数学中 的函数在编程语言中被称为纯函数

什么是副作用: 函数在除了返回值之外，对程序的运行环境造成的影响，比如：

- 修改了某个全局变量的值，或者，
- 使参数发生了变化，或者在控制台输出了东西。

- 读取，写入了文件
- 往数据库插入记录
- 发送一个 http 请求
- 打印/log
- 获取用户输入
- DOM 查询
- 访问系统状态

String String.format(fmt,...obj) 是纯函数吗？ 是

boolean equals(Object a, Object b); 是纯函数吗？ 是

double Math.random() 是纯函数吗？// 不是

Collections.sort(A,Comparator<A>) // 不是

// 针对相同的输入，它是否总是会得出相同的输出，并且不改变状态？ 如果答案是”是“，那么它就是纯函数

// 非纯函数应当理解为一个过程：它在做某件事情

// 传函数应当理解为一个求解，或者推断：它在尝试算出某个值，或者得出某个结论



纯函数:函数的一种特例： 函数没有副作用，不依赖外部参数，结果是确定的



好处：  可缓存，可并行（因为没有共享状态，不会有线程安全之类的问题）



柯里化 curry



```javascript
//Regex,String -> Boolean
match(/\s+/g, "hello world"); Boolean
// [ ' ' ]
//Regex -> String -> Boolean
match(/\s+/g)("hello world"); Boolean
// [ ' ' ]
// String -> Boolean
var hasSpaces = match(/\s+/g); String -> Boolean
// function(x) { return x.match(/\s+/g) }

hasSpaces("hello world"); Boolean
// [ ' ' ]

hasSpaces("spaceless"); Boolean
// null
Function<A,Boolean> -> Array<A> -> Array<A>
var filter = f => ary =>ary.filter(f);

(String -> Boolean) -> Array -> Array
filter(hasSpaces, ["tori_spelling", "tori amos"]);
// ["tori amos"]
Array -> Array
var findSpaces = filter(hasSpaces);
// function(xs) { return xs.filter(function(x) { return x.match(/\s+/g) }) }

findSpaces(["tori_spelling", "tori amos"]);
// ["tori amos"]

var noVowels = replace(/[aeiou]/ig);
// function(replacement, x) { return x.replace(/[aeiou]/ig, replacement) }

var censored = noVowels("*");
// function(x) { return x.replace(/[aeiou]/ig, "*") }

censored("Chocolate Rain");
// 'Ch*c*l*t* R**n'

replace(/[aeiou]/ig,"*","Chocolate Rain")
"Chocolate Rain".replace(/[aeiou]/ig,"*")
```

