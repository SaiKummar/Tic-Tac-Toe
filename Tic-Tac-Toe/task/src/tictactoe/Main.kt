package tictactoe

fun main() {
    //initialize empty grid
    val grid = Array<CharArray>(3) {CharArray(3) {' '}}
    var coordinates = ""
    var gameState = ""
    val turns = arrayOf('X', 'O')
    var turnIndex = 0 //X starts first
    printGrid(grid)
    do {
        do {
            print("Enter the coordinates: ")
            coordinates = readLine()!!
        } while (!checkInput(coordinates, grid))
        val xyCoordinates = getXY(coordinates)
        grid[xyCoordinates[0] - 1][xyCoordinates[1] - 1] = turns[turnIndex++ % 2]
        printGrid(grid)
        gameState = analyzeGame(grid)
    } while (gameState == "continue")
    println(gameState)
}

fun analyzeGame(grid: Array<CharArray>): String {
    var hasEmpty = false
    for (row in grid) {
        if (' ' in row) {
            hasEmpty = true
            break
        }
    }
    if (hasThree(grid, 'X')) {
        return "X wins"
    } else if (hasThree(grid, 'O')) {
        return "O wins"
    } else if (!hasEmpty) {
        return "Draw"
    } else {
        return "continue"
    }
}

fun checkInput(input: String, grid: Array<CharArray>): Boolean {
    //check for non digits
    for (char in input) {
        if (!char.isDigit() && !char.isWhitespace()) {
            println("You should enter numbers!")
            return false
        }
    }
    val coordinatesArr = getXY(input)
    //check for wrong coordinates
    for (coordinate in coordinatesArr) {
        if (coordinate !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            return false
        }
    }
    //check if cell is occupied
    if (grid[coordinatesArr[0] - 1][coordinatesArr[1] - 1] != ' ') {
        println("This cell is occupied! Choose another one!")
        return false
    }
    return true
}

fun getXY(input: String): IntArray {
    val arrayXY = IntArray(2)
    val stringArray = input.split(" ").toTypedArray()
    for (i in 0..1) {
            arrayXY[i] = stringArray[i].toInt()
    }
    return arrayXY
}

fun printGrid(grid: Array<CharArray>) {
    println("---------")
    for (row in grid) {
        print("| ")
        for (col in row) {
            print("$col ")
        }
        println("|")
    }
    println("---------")
}

fun hasThree(grid: Array<CharArray>, mark: Char): Boolean {
    loop@ for (i in grid) {
        for (j in i) {
            if (j != mark) {
                continue@loop
            }
        }
        return true
    }
    jump@ for (col in 0..2) {
        for (r in grid) {
            if (r[col] != mark) {
                continue@jump
            }
        }
        return true
    }
    if (grid[1][1] == mark) {
        if ((grid[0][0] == mark && grid[2][2] == mark) ||
            (grid[2][0] == mark && grid[0][2] == mark)) {
            return true
        }
    }
    return false
}