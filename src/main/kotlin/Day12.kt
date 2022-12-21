package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

private const val START_SYMBOL = 'S'

private const val END_SYMBOL = 'E'

class Day12 : AdventCalendarDay("input_day12.txt") {
    override fun part1Impl(): Int {
        val steps = 0

        val map = mutableListOf<List<Int>>()

        inputLines.forEach {map.add(it.map { c -> getTranformedValue(c)})}

        val absoluteStartPosition = map.flatten().indexOf(getTranformedValue(START_SYMBOL))
        val startPoint = Day14.Point(absoluteStartPosition%map[0].size, absoluteStartPosition/map[0].size)
        val absoluteEndPosition = map.flatten().indexOf(getTranformedValue(END_SYMBOL))
        val endPoint = Day14.Point(absoluteEndPosition%map[0].size, absoluteEndPosition/map[0].size)

        val path = mutableListOf<Day14.Point>()
        path.add(startPoint)
        val pathSize = getPath(startPoint, endPoint, path, map)

        println("Shortest Path is: $pathSize to go from $startPoint to $endPoint")

        return steps
    }

    private fun getPath(startPoint : Day14.Point, endPoint: Day14.Point, path: MutableList<Day14.Point>, map : List<List<Int>>) : Int{
        val possibilities = listOf(getPointUp(startPoint), getPointDown(startPoint), getPointRight(startPoint), getPointLeft(startPoint))
            .filter { isLegal(startPoint, it, map) && !path.contains(it)}
        if(possibilities.isEmpty()) {
            return Int.MAX_VALUE
        }
        if(possibilities.contains(endPoint)){
            //path.add(endPoint) as the path contains starting point, do not check in
            return path.size
        }
        var min = Int.MAX_VALUE
        possibilities.forEach {
            path.add(it)
            val tmp = getPath(it, endPoint, path, map)
            if(tmp == 26 && path.size == 25){
                println("possibility: ")
                path.forEach { node -> println(node) }
            }
            min = min.coerceAtMost(tmp)
            path.remove(it)
        }
        return min
    }

    private fun getTranformedValue(c : Char) : Int{
        return if (START_SYMBOL == c){
            'a'.code-1
        }else if(END_SYMBOL == c){
            'z'.code+1
        }else{c.code }
    }

    private fun getPointUp(startPoint: Day14.Point) : Day14.Point{
        return Day14.Point(startPoint.x , startPoint.y-1)
    }

    private fun getPointDown(startPoint: Day14.Point) : Day14.Point{
        return Day14.Point(startPoint.x , startPoint.y+1)
    }

    private fun getPointRight(startPoint: Day14.Point) : Day14.Point{
        return Day14.Point(startPoint.x+1 , startPoint.y)
    }

    private fun getPointLeft(startPoint: Day14.Point) : Day14.Point{
        return Day14.Point(startPoint.x-1 , startPoint.y)
    }

    private fun isLegal(startPoint: Day14.Point, endPoint: Day14.Point, map : List<List<Int>>) : Boolean {
        return endPoint.x >= 0 && endPoint.y >= 0 && endPoint.x < map[0].size && endPoint.y < map.size
                && map[endPoint.y][endPoint.x] - map[startPoint.y][startPoint.x] <= 1 /* at most 1 if climbing or anything if descending*/
    }

    override fun part2Impl(): BigInteger {
        TODO("Not yet implemented")
    }
}