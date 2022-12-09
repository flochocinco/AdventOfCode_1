abstract class AdventCalendarDay(inputFileName : String) {

    val inputLines = object {}.javaClass.getResourceAsStream(inputFileName)?.bufferedReader()?.readLines()!!

    fun part1() {
        println("Part1: " + part1Impl())
    }
    fun part2() {
        println("Part2: " + part2Impl())
    }

    protected abstract fun part2Impl() : Int
    protected abstract fun part1Impl() : Int
}