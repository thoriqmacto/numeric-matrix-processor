fun main() {
    // write your code here
    val size = readln().toInt()
    val mutList: MutableList<Int> = mutableListOf()
    val newList: MutableList<Int> = mutableListOf()

    repeat(size){
        newList.add(0)
    }

    for (i in 0 until size) {
        mutList.add(readln().toInt())
    }

    for (i in 0 until size){
        if (i == size-1){
            newList[0] = mutList[i]
        }else{
            newList[i+1] = mutList[i]
        }
    }

    println(newList.joinToString(" "))
}