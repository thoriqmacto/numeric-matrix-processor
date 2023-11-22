fun main() {
    // write your code here
    val N = readln().toInt()
    val lst = mutableListOf<Int>()
    repeat(N){
        lst.add(readln().toInt())
    }
    val M = readln().toInt()

    var count = 0
    for(i in 0 until lst.size){
        if (M == lst[i]){
            count++
        }
    }

    println(count)
}