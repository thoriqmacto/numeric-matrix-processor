class Vehicle(val name: String) {
    inner class Engine(val horsePower: Int) {
        fun start() {
            println("RRRrrrrrrr....")
        }

        // create function printHorsePower()
        fun printHorsePower(){
            println("The $name vehicle has $horsePower horsepower.")
        }
    }
}