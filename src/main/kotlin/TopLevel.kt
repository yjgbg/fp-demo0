
// 顶层函数,一等公民

// java中并不支持
// 但是如果我们将java中的类视为命名空间或者包，则类中的静态函数便可被视为顶层函数
fun main() {
    printHello()
}

fun printHello() {
    println("hello")
}