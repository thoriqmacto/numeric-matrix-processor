fun main() {
    // put your code here
    val str = "abcdefghijklmnopqrstuvwxyz"
    val inp = readln()
    var out = ""

    for (ch in str){
        if (ch !in inp){
            out += ch
        }
    }
    println(out)
}

