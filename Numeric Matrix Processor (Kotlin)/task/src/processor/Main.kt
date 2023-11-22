package processor
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.*

fun main() {
    do{
        println("1. Add matrices")
        println("2. Multiply matrix by a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrix")
        println("5. Calculate a determinant")
        println("6. Inverse matrix")
        println("0. Exit")
        print("Your choice: ")
        val option = readln().toInt()

        if (option == 1 || option == 3){
            print("Enter size of first matrix:")
            val sizeMatrixA = readln()
            val rowMatrixA = sizeMatrixA.split(" ")[0]
            val colMatrixA = sizeMatrixA.split(" ")[1]
            println("Enter first matrix:")
            val matrixA = createMatrixDouble(rowMatrixA.toInt(),colMatrixA.toInt())
            print("Enter size of second matrix:")
            val sizeMatrixB = readln()
            val rowMatrixB = sizeMatrixB.split(" ")[0]
            val colMatrixB = sizeMatrixB.split(" ")[1]
            println("Enter second matrix:")
            val matrixB = createMatrixDouble(rowMatrixB.toInt(),colMatrixB.toInt())

            if (option == 1){
                println("The result is:")
                val result = matrixOperationAddition(matrixA,matrixB)
                if (result.size > 0){
                    printMatrixDouble(result)
                }else{
                    println("The operation cannot be performed.")
                }
            }else{
                val result = matrixOperationMultiplication(matrixA,matrixB)
                if (result.size > 0){
                    printMatrixDouble(result)
                }else{
                    println("The operation cannot be performed.")
                }
            }
        }else if(option == 2){
            print("Enter matrix size:")
            val sizeMatrixA = readln()
            val rowMatrixA = sizeMatrixA.split(" ")[0]
            val colMatrixA = sizeMatrixA.split(" ")[1]
            println("Enter matrix:")
            val matrixA = createMatrixDouble(rowMatrixA.toInt(),colMatrixA.toInt())
            print("Enter constant:")
            val constDouble = readln().toDouble()
            println("The result is:")
            val result = matrixOperationMultiplicationWithConstantDouble(constDouble,matrixA)
            printMatrixDouble(result)
        }else if(option == 4){
            println()
            println("1. Main diagonal")
            println("2. Side diagonal")
            println("3. Vertical line")
            println("4. Horizontal line")
            print("Your choice:")
            val selectedTranspose = readln().toInt()
            print("Enter matrix size:")
            val sizeMatrix = readln()
            val rowMatrixA = sizeMatrix.split(" ")[0]
            val colMatrixA = sizeMatrix.split(" ")[1]
            println("Enter matrix:")
            val matrix = createMatrixDouble(rowMatrixA.toInt(),colMatrixA.toInt())

            println("The result is:")

            when (selectedTranspose) {
                1 -> {
                    val result = matrixOperationTranspose(matrix,"MAIN")
                    printMatrixDouble(result)
                }
                2 -> {
                    val result = matrixOperationTranspose(matrix,"SIDE")
                    printMatrixDouble(result)
                }
                3 -> {
                    val result = matrixOperationTranspose(matrix,"VERTICAL")
                    printMatrixDouble(result)
                }
                4 -> {
                    val result = matrixOperationTranspose(matrix,"HORIZONTAL")
                    printMatrixDouble(result)
                }
            }
        }else if(option == 5){
            print("Enter matrix size: ")
            val sizeMatrix = readln()
            val rowMatrixA = sizeMatrix.split(" ")[0]
            val colMatrixA = sizeMatrix.split(" ")[1]
            println("Enter matrix:")
            val matrix = createMatrixDouble(rowMatrixA.toInt(),colMatrixA.toInt())

            println("The result is:")

            println(matrixOperationCalculateDeterminant(matrix))
        }else if(option == 6){
            print("Enter matrix size: ")
            val sizeMatrix = readln()
            val rowMatrixA = sizeMatrix.split(" ")[0]
            val colMatrixA = sizeMatrix.split(" ")[1]
            println("Enter matrix:")
            val matrix = createMatrixDouble(rowMatrixA.toInt(),colMatrixA.toInt())

            println("The result is:")

            val result = matrixOperationInverseMatrix(matrix)
            if (result.size > 0){
                printMatrixDouble(result)
            }else{
                println("This matrix doesn't have an inverse.")
            }
        }
        println()
    }while(option != 0)
}

fun matrixOperationInverseMatrix(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    val outputMatrix = mutableListOf<MutableList<Double>>()
    val determinant = matrixOperationCalculateDeterminant(matrix)

    if (determinant != 0.0){
        val cofactorMatrix:MutableList<MutableList<Double>> = matrixOperationCreateCofactorMatrix(matrix)
        val cofactorMatrixTranspose = matrixOperationTranspose(cofactorMatrix,"MAIN")
        outputMatrix += matrixOperationMultiplicationWithConstantDouble(1.0/determinant, cofactorMatrixTranspose)
        return outputMatrix
    }

    return outputMatrix
}

fun matrixOperationCreateCofactorMatrix(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    val outputMatrix = mutableListOf<MutableList<Double>>()

    repeat(matrix.size){
        val rowTemp = mutableListOf<Double>()
        repeat(matrix.size){
            rowTemp.add(0.0)
        }
        outputMatrix += rowTemp
    }

    for (i in 0 until matrix.size){
        for (j in 0 until matrix.size){
            val subMatrix = createSubMatrixGeneric(matrix,matrix.size,i,j)
            val subDeterminant = matrixOperationCalculateDeterminant(subMatrix)
            outputMatrix[i][j] = subDeterminant * Math.pow(-1.0,(i+j).toDouble())
        }
    }

    return outputMatrix
}

fun matrixOperationCalculateDeterminant(matrix: MutableList<MutableList<Double>>): Double {
    if(matrix.size == 1){
        return matrix[0][0]
    }

    var determinant = 0.0

    for (i in 0 until matrix.size){
        val subMatrix = createSubMatrix(matrix, matrix.size, i)
        val subDeterminant = matrixOperationCalculateDeterminant(subMatrix)
        determinant += matrix[0][i] * subDeterminant * Math.pow(-1.0, i.toDouble())
    }

    return determinant
}

fun createSubMatrix(matrix: MutableList<MutableList<Double>>, size: Int, columnToRemove: Int): MutableList<MutableList<Double>> {
    val subMatrix = mutableListOf<MutableList<Double>>()
    var rowIndex = 0

    repeat(size-1){
        val rowTemp = mutableListOf<Double>()
        repeat(size-1){
            rowTemp.add(0.0)
        }
        subMatrix += rowTemp
    }

    for (i in 1 until size){
        var columnIndex = 0
        for (j in 0 until size){
            if (j != columnToRemove){
                subMatrix[rowIndex][columnIndex] = matrix[i][j]
                columnIndex++
            }
        }
        rowIndex++
    }

    return subMatrix
}

fun createSubMatrixGeneric(matrix:MutableList<MutableList<Double>>, size:Int,rowToRemove:Int,columnToRemove:Int):MutableList<MutableList<Double>>{
    val subMatrix = mutableListOf<MutableList<Double>>()
    var rowIndex = 0

    repeat(size-1){
        val rowTemp = mutableListOf<Double>()
        repeat(size-1){
            rowTemp.add(0.0)
        }
        subMatrix += rowTemp
    }

    for (i in 0 until size) {
        if (i == rowToRemove) {
            continue
        }

        var columnIndex = 0
        for (j in 0 until size) {
            if (j == columnToRemove) {
                continue
            }

            subMatrix[rowIndex][columnIndex] = matrix[i][j]
            columnIndex++
        }
        rowIndex++
    }

    return subMatrix
}

fun matrixOperationTranspose(matrix: MutableList<MutableList<Double>>, mode: String): MutableList<MutableList<Double>> {
    val rowMatrix = sizeOfMatrix(matrix).split(" x ")[0].toInt()
    val colMatrix = sizeOfMatrix(matrix).split(" x ")[1].toInt()
    val outputMatrix = mutableListOf<MutableList<Double>>()

    repeat(rowMatrix){
        val rowTemp = mutableListOf<Double>()
        repeat(colMatrix){
            rowTemp += 0.0
        }
        outputMatrix += rowTemp
    }

    when(mode) {
        "MAIN" -> {
            for (i in 0 until rowMatrix) {
                for (j in 0 until colMatrix) {
                    outputMatrix[j][i] = matrix[i][j]
                }
            }
        }
        "SIDE" -> {
            for (i in 0 until rowMatrix) {
                for (j in 0 until colMatrix) {
                    outputMatrix[colMatrix-1-j][colMatrix-1-i] = matrix[i][j]
                }
            }
        }
        "VERTICAL" -> {
            for (i in 0 until rowMatrix) {
                for (j in 0 until colMatrix) {
                    outputMatrix[i][colMatrix-1-j] = matrix[i][j]
                }
            }
        }
        "HORIZONTAL" -> {
            for (i in 0 until rowMatrix) {
                for (j in 0 until colMatrix) {
                    outputMatrix[colMatrix-1-i][j] = matrix[i][j]
                }
            }
        }
    }

    return outputMatrix
}

fun printMatrixDouble(matrix: MutableList<MutableList<Double>>) {
    for (i in 0 until matrix.size){
        for (j in 0 until matrix[0].size){
            print(matrix[i][j].toString())
            if (j < matrix[0].size-1){
                print(" ")
            }
        }
        println()
    }
}

fun matrixOperationMultiplication(matrixA: MutableList<MutableList<Double>>, matrixB: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    val matrixAcol = sizeOfMatrix(matrixA).split(" x ")[1]
    val matrixBrow = sizeOfMatrix(matrixB).split(" x ")[0]

    val matrixArow = sizeOfMatrix(matrixA).split(" x ")[0].toInt()
    val matrixBcol = sizeOfMatrix(matrixB).split(" x ")[1].toInt()
    val outputMatrix = mutableListOf<MutableList<Double>>()

    if (matrixAcol == matrixBrow){
        repeat(matrixArow){
            val rowTemp = mutableListOf<Double>()
            repeat(matrixBcol){
                rowTemp += 0.0
            }
            outputMatrix += rowTemp
        }

        for (i in 0 until matrixArow){
            for (j in 0 until matrixBcol){
                var sum = 0.0
                for (k in 0 until matrixAcol.toInt()){
                    sum += matrixA[i][k] * matrixB[k][j]
                }
                outputMatrix[i][j] = sum
            }

        }
        return outputMatrix
    }

    return outputMatrix
}

fun createMatrixDouble(rowMatrix: Int, colMatrix: Int): MutableList<MutableList<Double>> {
    val outputMatrix = mutableListOf<MutableList<Double>>()
    repeat(rowMatrix){
        val readRow = readln()
        val rowTemp = mutableListOf<Double>()

        var i = 0
        repeat(colMatrix){
            rowTemp += readRow.split(" ")[i].toDouble()
            i++
        }
        outputMatrix += rowTemp
    }
    return outputMatrix
}

fun matrixOperationMultiplicationWithConstantDouble(constDouble: Double, matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    val outputMatrix = mutableListOf<MutableList<Double>>()

    repeat(matrix.size){
        val rowTemp = mutableListOf<Double>()
        repeat(matrix[0].size){
            rowTemp += 0.0
        }
        outputMatrix += rowTemp
    }

    for (i in 0 until matrix.size){
        for (j in 0 until matrix[0].size){
            outputMatrix[i][j] = constDouble * matrix[i][j]
        }
    }

    return outputMatrix
}

fun printMatrix(matrix: MutableList<MutableList<Int>>) {
    for (i in 0 until matrix.size){
        for (j in 0 until matrix[0].size){
            print(matrix[i][j].toString())
            if (j < matrix[0].size-1){
                print(" ")
            }
        }
        println()
    }
}

fun matrixOperationAddition(matrixA: MutableList<MutableList<Double>>, matrixB: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    val outputMatrix = mutableListOf<MutableList<Double>>()

    if (sizeOfMatrix(matrixA) == sizeOfMatrix(matrixB)){
        repeat(matrixA.size){
            val rowTemp = mutableListOf<Double>()
            repeat(matrixA[0].size){
                rowTemp += 0.0
            }
            outputMatrix += rowTemp
        }

        for (i in 0 until matrixA.size){
            for (j in 0 until matrixA[0].size){
                outputMatrix[i][j] = matrixA[i][j] + matrixB[i][j]
            }
        }
        return outputMatrix
    }

    return outputMatrix
}

fun sizeOfMatrix(matrixA: MutableList<MutableList<Double>>): String {
    val row = matrixA.size
    val col = matrixA[0].size
    return "$row x $col"
}

fun sizeOfMatrixInt(matrix: MutableList<MutableList<Int>>): String {
    val row = matrix.size
    val col = matrix[0].size
    return "$row x $col"
}

fun createMatrixInt(rowMatrixA: Int, colMatrixA: Int): MutableList<MutableList<Int>> {
    val outputMatrix = mutableListOf<MutableList<Int>>()
    repeat(rowMatrixA){
        val readRow = readln()
        val rowTemp = mutableListOf<Int>()

        var i = 0
        repeat(colMatrixA){
            rowTemp += readRow.split(" ")[i].toInt()
            i++
        }
        outputMatrix += rowTemp
    }
    return outputMatrix
}

fun roundToFixedDigits(number: Double, digits: Int): Double {
    require(digits >= 0) { "Number of digits must be non-negative." }

    val bd = BigDecimal(number)
    return bd.setScale(digits, RoundingMode.HALF_UP).toDouble()
}