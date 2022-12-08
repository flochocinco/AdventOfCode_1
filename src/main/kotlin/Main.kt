
fun main() {

    day3()
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
    pts += if(input.contains('A')){
        1
    }else if(input.contains('B')){
        2
    }else {
        3
    }
    return pts
}


/**
 * compute point in order to win -> 6 + compute point of played figure
 */
fun makeWin(input : String) : Int {
    var pts = victoryPoints
    pts += if(input.contains('A')){
        2
    }else if(input.contains('B')){
        3
    }else {
        1
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

/**
 * Part1: find common letter in two strings and get sum of the corresponding integer code
 */
fun day3() {
    println("Advent of Code 2022 Day 3")
    println("https://adventofcode.com/2022/day/3")

    val lines = object {}.javaClass.getResourceAsStream("input_day3.txt")?.bufferedReader()?.readLines()

    //Part 1
    val priorities = populateArray(lines!!)
    println("Part 1: Sum of the priorities: " + computePrioritiesSum(priorities))

    //Part 2
    var part2Result = 0
    var tmp = 0
    while(tmp < lines.size){
        part2Result += getPriority(findCommonLetter(lines[tmp], lines[tmp+1], lines[tmp+2]))
        tmp+=3
    }
    println("Part 2 : Sum of the priorities of those items: $part2Result")
}

fun computePrioritiesSum(priorities : IntArray) : Int{
    var prioritiesSum = 0
    var index = 0
    while(++index < priorities.size){
        prioritiesSum += index * priorities[index]
    }
    return prioritiesSum
}

/**
 * create an array where index is the value of the priority. The array value at specific index is the number of occurrence of one letter.
 *
 * eg: array[2] = 3 means that 'b' ('a' is 1) has been found 3 times
 */
fun populateArray(lines : List<String>) : IntArray{
    val priorities = IntArray(53) {0}
    lines.forEach {
        val index = getPriority(splitAndFindCommon(it))
        priorities[index] += 1
    }
    return priorities
}

fun getPriority(letter : Char) : Int{
    return if(letter.code > 97){
        letter.code - 96
    }else{
        letter.code - 38
    }
}

fun splitAndFindCommon(input: String): Char{
    return findCommon(input.substring(0, input.length/2), input.substring(input.length/2))
}

/**
 * find first letter present in 3 given Strings
 */
fun findCommonLetter(input1 : String, input2 : String, input3 : String) : Char {
    return input1.first { input2.contains(it) && input3.contains(it) }
}

/**
 * find first letter present in 2 given Strings
 */
fun findCommon(input1 : String, input2 : String) : Char{
    return input1.first { input2.contains(it) }
}