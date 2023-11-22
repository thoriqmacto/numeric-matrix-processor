fun main() {
    // write your code here
    val times = readln().toInt()
    val container = mutableListOf<Int>()
    repeat(times){
        container.add(readln().toInt())
    }

    var maxIndex = 0

    for (i in container.indices){
        if (container[i] > container[maxIndex]) {
            maxIndex = i
        }
    }

    println(maxIndex)
}