// TODO: provide three functions here
fun identity(x:Int):Int{
    return x
}

fun half(x:Int):Int{
    return x / 2
}

fun zero(x:Int):Int{
    return 0
}

fun generate(functionName: String): (Int) -> Int {
    // TODO: provide implementation here
    when(functionName){
        "half" -> return ::half
        "identity" -> return ::identity
    }
    return ::zero
}