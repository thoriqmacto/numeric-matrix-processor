import kotlin.math.*

fun main() {
    // write your code here
    val population = readln().toInt()
    println(ceil(Math.pow(population.toDouble(),1.0/3.0)).toInt())
}