class Vehicle {
    inner class Engine {
        fun start() {
            println("RRRrrrrrrr....")
        }
    }
}
// do not touch the class above

fun main() {
    // start your vehicle, put your code here
    val car = Vehicle()
    val carStart = car.Engine().start()
}