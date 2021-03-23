# 函数式编程

函数式编程（英语：functional programming）或称函数程序设计、泛函编程，是一种编程范式，它将电脑运算视为函数运算，
并且避免使用程序状态以及易变对象。其中，λ演算为该语言最重要的基础。而且，λ演算的函数可以接受函数作为输入参数和输出返回值
。[维基百科](https://zh.wikipedia.org/wiki/%E5%87%BD%E6%95%B0%E5%BC%8F%E7%BC%96%E7%A8%8B)

## Immutability不可变性

包含两层意思
- 没有变量，只有常量（java中使用final修饰所有变量）
- 数据一旦被创建出来，就不能被修改了 （比如一个数组被创建出来以后，就不能再增、删、
  改里面的元素了；一个Map被创建出来以后，里面的key-value entry也是不能再变的）
为什么这么设计?
  函数式编程思想认为数据的可变导致了程序的复杂（此处举例）
  
  一切由并发引起的问题，一切由多线程、多进程引起的问题，都是由变量可变引起的！！！如果变量不可变，那么所有的竞争问题、死锁问题、并发更新问题都将烟消云散
  
Immutable有什么好处?
- 线程安全
- 可以被重复使用
- 当一个对象是不可变的，那么需要拷贝这个对象的内容时，就不用复制它的本身而只是复制它的地址，
  复制地址（通常一个指针的大小）只需要很小的内存空间，具有非常高的效率
针对自己设计的不可变对象（类），也可以通过类似的方式，在参数相同的方式下返回相同对象，降低内存开销

```java
import java.util.HashMap;
import java.util.Map;

public final class R {
  private final Integer code;
  private R(int integer) {
    this.code = integer;
  }
  private static final Map<Integer, R> CACHE = new HashMap<>();
  // 如果该对象的构造过程更为复杂，则收益会更高
  public static R of(int code) { 
    final var res0 = CACHE.get(code);
    if (res0!=null) return res0;
    final var res = new R(code);
    CACHE.put(code,new R(code));
  }
}
```

## 纯函数
引："函数"式编程，函数是指数学中的函数，数学中的函数在编程语言中被称
为纯函数，具有这两个特点：
- 此函数在相同的输入值时，需产生相同的输出
- 该函数不能有语义上可观察的函数副作用

什么是副作用: 函数在除了返回值之外，对程序的运行环境造成的影响，比如:

- 修改了某个全局变量的值，或者，
- 使参数发生了变化，或者在控制台输出了东西。

- 读取，写入了文件
- 往数据库插入记录
- 发送一个 http 请求
- 打印/log
- 获取用户输入
- DOM 查询
- 访问系统状态

```haskell
-- 数学函数
f(x) = x * 2
f(x) = x + 3
```
```javascript
var a = 5;
function A(b) {
    return a + b;
}
A(5);
// 显然这不是个纯函数
const a = 5;
function A(b) {
    return a + b;
}
A(5);
// 显然这是个纯函数

function A(a) {
    return function(b) {
        return a + b;
    }
}
var B = A(5);
// 这是纯函数，涉及到函数的判等条件：如果两个函数的定义域，值域都相同，而且代表的映射关系都一直，那么这两个函数是相等的
```



## 柯里化

把接受多个参数的函数变换成接受一个单一参数（最初函数的第一个参数）的函数，并且返回接受余下的参数而且返回结果的新函数的技术

```java
public class Curry {
  public static <A,B,C> Function<A, Function<B,C>> curry(BiFunction<A,B,C> function) {
    return a -> b -> function.apply(a,b);
  }
}
```

启示: Function<A,Function<B,C>>和BiFunction<A,B,C>是等价的
即： a -> b -> a+b 等价与(a,b) -> a+b
可以通过这种方式构造任意参数个数的函数：举例
jdk中没有三个参数的函数(```TriFunction<A,B,C,D>``` ?)，但是可以通过下面的方式做出来等价的效果

```Function<A,Function<B,Function<C,D>>> func = a -> b -> c -> a+b+c;```
## 函数组合
```haskell
pipe::(a,b,c) => (a -> b) -> (b -> c) -> a -> c
pipe f1 f2 a = f2 (f1 a)
pipe::(a,b,c,d) => (a -> b) -> (b -> c) -> (c -> d) -> a -> d
-- 直接使用中缀写法，发现了结合律
pipe f1 f2 f3 = f1 `pipe` f2 `pipe` f3
-- 或者 pipe f1 f2 f3 = f1 `pipe` f2 `pipe` f3
```
java中的等价实现

### pointfree风格
个人见解：并不太适合java，java不够灵活，无法
像类似js，haskell等语言中那么清晰的代码语义

[pointfree](https://www.ruanyifeng.com/blog/2017/03/pointfree.html)

站在数据流动的角度看待程序

把数据处理的过程，定义成一种与参数无关的合成运算，不需要用到代表数据的那个参数，只要把一些简单的运算步骤合成在一起即可

```Function.andThen```

Pointfree 的本质就是使用一些通用的函数，组合出各种复杂运算，并且在组合过程中不关心具体数据细节

柯里化使得pointfree更加自然（原因：一个参数，一个返回值，数据流）