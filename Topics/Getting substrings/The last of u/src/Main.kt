fun main() {
    val string = readln()
    val afterU = string.substringAfterLast('u').uppercase()
    println(string.replaceAfterLast('u',afterU))
}