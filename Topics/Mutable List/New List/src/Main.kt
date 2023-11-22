fun solution(numbers: List<Int>, number: Int): MutableList<Int> {
    // put your code here
    val outputList = mutableListOf<Int>()
    outputList += numbers
    outputList.add(number)
    return outputList
}