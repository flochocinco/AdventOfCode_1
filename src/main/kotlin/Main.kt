
fun main() {

    day2()
}

fun day1(){
    println("Advent of Code 2022 Day 1")
    println("https://adventofcode.com/2022/day/1")

    val lines = object {}.javaClass.getResourceAsStream("input.txt")?.bufferedReader()?.readLines()

    var amounts = mutableListOf<Int>()
    var tmp = 0
    if (lines != null) {
        for(line in lines){
            if(line.isEmpty()){
                amounts.add(tmp)
                tmp = 0
            }else{
                tmp += Integer.valueOf(line)
            }
        }
    }

    amounts.sort()
    amounts.reverse()
    amounts = amounts.subList(0, 3)//only get 1st item for part 1
    println("Sum of the 3 first: " + amounts.sum())
}

fun day2(){
    println("Advent of Code 2022 Day 2")
    println("https://adventofcode.com/2022/day/2")

    val lines = object {}.javaClass.getResourceAsStream("input_day2.txt")?.bufferedReader()?.readLines()

    //PART 1
    //A X Rock 1pt
    //B Y Paper 2pts
    //C Z Scissors 3pts
    println("Total Points Part 1: " + computePoints(lines!!))

    //PART 2
    var pts = 0
    for(line in lines){
        pts += if(line.contains("X")){
            makeLose(line)
        }else if(line.contains("Y")){
            makeDraw(line)
        }else {
            makeWin(line)
        }
    }

    println("Total Points Part 2: $pts")
}

/**
 * compute point in order to lose -> compute point of played figure
 */
fun makeLose(input : String) : Int {
    return if(input.contains('A')){
        3
    }else if(input.contains('B')){
        1
    }else {
        2
    }
}

/**
 * compute point in order to draw -> 3 + compute point of played figure
 */
fun makeDraw(input : String) : Int {
    var pts = drawPoints
    if(input.contains('A')){
        pts+= 1
    }else if(input.contains('B')){
        pts+= 2
    }else {
        pts+= 3
    }
    return pts
}


/**
 * compute point in order to win -> 6 + compute point of played figure
 */
fun makeWin(input : String) : Int {
    var pts = victoryPoints
    if(input.contains('A')){
        pts+= 2
    }else if(input.contains('B')){
        pts+= 3
    }else {
        pts+= 1
    }
    return pts
}

const val drawPoints = 3
const val victoryPoints = 6

fun computePoints(lines : List<String>) : Int{

    var points: Int = lines.count { it.contains("X") }
    points += 2 * lines.count { it.contains("Y") }
    points += 3 * lines.count { it.contains("Z") }
    points += drawPoints * lines.count { it.contains("A X") ||  it.contains("B Y") || it.contains("C Z") }
    points += victoryPoints * lines.count { it.contains("A Y")  ||  it.contains("B Z") || it.contains("C X") }
    return points
}