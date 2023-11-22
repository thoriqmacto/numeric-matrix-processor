fun main() {
    // put your code here
    val digInput = readln().toLong()
    val lastDigit = digInput % 10
    val secondDigit = (digInput / 10) % 10
    val thirdDigit = (digInput / 100) % 10

    print(lastDigit.toString() + secondDigit.toString() + thirdDigit.toString())
}