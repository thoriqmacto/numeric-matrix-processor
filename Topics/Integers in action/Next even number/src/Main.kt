fun main() {
    // put your code here
    val N = readln().toInt()
    var out = 0
    if (N % 2 == 0){
        out = N + 2
    }else{
        out = N + 1
    }

    println(out)
}