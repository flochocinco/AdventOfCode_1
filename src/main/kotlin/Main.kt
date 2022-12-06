
fun main() {
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