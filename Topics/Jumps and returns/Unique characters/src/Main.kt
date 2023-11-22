fun main() {
    // put your code here
    val str = readln()
    val arrStr = mutableListOf<Char>()
    val arrCount = mutableListOf<Int>()
    var i = 0
    for (s in str){
        val addCond = !arrStr.contains(s)
        if (addCond){
            arrStr.add(i,s)
            arrCount.add(i,1)
            i++
        }else{
            val idxChar = arrStr.indexOf(s)
            arrCount[idxChar] += 1
        }
    }
//    println(arrStr.joinToString())
//    println(arrCount.joinToString())

    var j = 0
    for (el in arrCount){
        if (el == 1){
            j++
        }
    }
    println(j)
}