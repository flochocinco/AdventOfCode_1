
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
    println("Total Points: " + computePoints(lines!!))

    //PART 2
    var modified = mutableListOf<String>()
    lines.forEach {modified.add(it.replace("X", makeLose(it))) }
}

fun makeLose(input : String) : String {
    var output:String
    if(input.contains('A')){
        output = input.replace("X","Z")
    }else if(input.contains('B')){
        output = input.replace("X","X")
    }else{
        output = input.replace("X","Y")
    }
    return output
}

fun computePoints(lines : List<String>) : Int{
    val drawPoints = 3
    val victoryPoins = 6
    var points: Int = lines.count { it.contains("X") }
    points += 2 * lines.count { it.contains("Y") }
    points += 3 * lines.count { it.contains("Z") }
    points += drawPoints * lines.count { it.contains("A X") ||  it.contains("B Y") || it.contains("C Z") }
    points += victoryPoins * lines.count { it.contains("A Y")  ||  it.contains("B Z") || it.contains("C X") }
    return points
}