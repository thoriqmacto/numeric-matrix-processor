// write your function here
fun sum(a:Int,b:Int,c:Int):Int{
    return a + b + c
}

fun main() {    
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()

    println(sum(a, b, c))
}