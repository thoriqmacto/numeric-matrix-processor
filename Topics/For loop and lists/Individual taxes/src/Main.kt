fun main() {
    // write your code here
    val cpy = readln().toInt()
    val incTax = mutableListOf<Int>()

    for (i in 0 until cpy * 2){
        incTax.add(readln().toInt())
    }

    var largestPay = 0
    var cpyIndex = 0
    for (i in 0 until cpy){
        var totalPay: Int = incTax[i] * incTax[i+cpy]
        if (totalPay > largestPay) {
            largestPay = totalPay
            cpyIndex = i+1
        }
//        println("$i $totalPay" )
    }

    println(cpyIndex)
}