fun main() {
    // put your code here
    val inp = readln()
    val regex = Regex("[0-9]")
    for (i in inp){
        if(regex.matches(i.toString())){
           println(i)
           return
        }
    }
}