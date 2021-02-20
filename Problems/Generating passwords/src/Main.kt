import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val upperCase = scanner.nextInt()
    val lowerCase = scanner.nextInt()
    val digits = scanner.nextInt()
    val total = scanner.nextInt()
    var password = ""
    val types = arrayOf(0, 0, 0) // 0-upper, 1-lower, 2-digit
    var i = 0
    var type = -1
    
    while (i < total) {
        if (types[0] < upperCase && types[1] < lowerCase && types[2] < digits) {
            type = (0..2).random()
            types[type] += 1
        } else if (types[0] == upperCase && types[1] < lowerCase && types[2] < digits) {
            type = (1..2).random()
            types[type] += 1
        } else if (types[0] == upperCase && types[1] == lowerCase && types[2] < digits) {
            type = 2
            types[type] += 1
        } else if (types[0] < upperCase && types[1] == lowerCase && types[2] < digits) {
            type = arrayOf(0, 2).random()
            types[type] += 1
        } else if (types[0] < upperCase && types[1] == lowerCase && types[2] == digits) {
            type = 0
            types[type] += 1
        } else if (types[0] == upperCase && types[1] < lowerCase && types[2] == digits) {
            type = 1
            types[type] += 1
        } else if (types[0] < upperCase && types[1] < lowerCase && types[2] == digits) {
            type = (0..1).random()
            types[type] += 1
        } else if (types[0] == upperCase && types[1] == lowerCase && types[2] == digits && i < total) {
            type = (0..2).random()
            types[type] += 1
        } 
        password += genRandom(password, type)
        ++i
    }
    println(password)
}

fun genRandom(pass: String, type: Int): Char {
    val myRange = when (type) {
        0 -> '\u0041'..'\u005A'
        1 -> '\u0061'..'\u007A'
        2 -> '\u0030'..'\u0039'
        else -> '\u0030'..'\u0039'
    }
    var randChar = '\u0000'
    do {
        randChar = myRange.random()
    } while (randChar == pass.lastOrNull())
    return randChar
}
