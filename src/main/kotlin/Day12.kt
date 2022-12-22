package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger
import kotlin.math.abs

private const val START_SYMBOL = 'S'

private const val END_SYMBOL = 'E'

class Day12 : AdventCalendarDay("input_day12.txt") {

    class Edge(x: Int, y : Int) : Day14.Point(x,y) {
        var shortestDistanceFromStart = Int.MAX_VALUE
    }

    override fun part1Impl(): Int {
        var steps = Int.MAX_VALUE

        val map = mutableListOf<List<Int>>()

        inputLines.forEach {map.add(it.map { c -> getTranformedValue(c)})}

        val absoluteStartPosition = map.flatten().indexOf(getTranformedValue(START_SYMBOL))
        val startPoint = Day14.Point(absoluteStartPosition%map[0].size, absoluteStartPosition/map[0].size)
        val absoluteEndPosition = map.flatten().indexOf(getTranformedValue(END_SYMBOL))
        val endPoint = Day14.Point(absoluteEndPosition%map[0].size, absoluteEndPosition/map[0].size)

        val path = mutableListOf<Day14.Point>()
        path.add(startPoint)

        val pathSize = getPath(startPoint, endPoint, path, map)
        steps = steps.coerceAtMost(pathSize)
        println("Shortest Path is: $pathSize to go from $startPoint to $endPoint")



        return steps
    }

    val leadingToVoid = mutableSetOf<Day14.Point>()
    var counter = 0L
    private fun getPath(startPoint : Day14.Point, endPoint: Day14.Point, path: MutableList<Day14.Point>, map : List<List<Int>>) : Int{
        counter++
        println("${counter}: Current Chat value : ${map[startPoint.y][startPoint.x].toChar()} at point ${startPoint.x+1};${startPoint.y+1}")
        //println("Path $path")
        var possibilities =
            listOf(getPointRight(startPoint), getPointUp(startPoint), getPointDown(startPoint), getPointLeft(startPoint))
                .filter { !leadingToVoid.contains(it) && isLegal(startPoint, it, map) && !path.contains(it)}
                .filter { map[it.y][it.x] >= map[startPoint.y][startPoint.x] } //climb first if possible
                .sortedWith(comparator = { first, second ->
                    val charValue = map[second.y][second.x].compareTo(map[first.y][first.x])
                    if(charValue != 0){
                        return@sortedWith charValue
                    }
                    val d1 = getDistance(first!!, endPoint)
                    val d2 = getDistance(second!!, endPoint)

                    if(d1 != d2){
                        return@sortedWith d1.compareTo(d2)
                    }
                    return@sortedWith abs(endPoint.y-first!!.y).coerceAtLeast(abs(endPoint.x-first.x))
                        .coerceAtMost(abs(endPoint.y-second!!.y).coerceAtLeast(abs(endPoint.x-second.x)))
                })
                //.sortedByDescending { abs(it.x-endPoint.x).coerceAtLeast((it.y-endPoint.y))}
                //.sortedByDescending { map[it.y][it.x] }
                .toList()
        //keep only cell which make us climb
        //possibilities = possibilities.filter { map[it.y][it.x] == map[possibilities[0].y][possibilities[0].x] }
        //now priority on shortest path
        //possibilities.sortedBy { abs(it.x-endPoint.x) + abs(it.y-endPoint.y) }
        /*possibilities = possibilities.filter { map[it.y][it.x] == map[possibilities[0].y][possibilities[0].x] }
        possibilities.sortedBy { (endPoint.x-it.x) + (endPoint.y-it.y)}
            .sortedByDescending { map[it.y][it.x] }*/
        if(possibilities.isEmpty()) {
            return Int.MAX_VALUE
        }
        if(possibilities.contains(endPoint)){
            //path.add(endPoint) as the path contains starting point, do not check in
            println("Found path of length: ${path.size}")
            return path.size
        }
        leadingToVoid.addAll(possibilities)
        val size = possibilities.minOf {
            path.add(it)
            val tmp = getPath(it, endPoint, path, map)
            path.remove(it)
            tmp
        }
        leadingToVoid.removeAll(possibilities.toSet())
        return size
    }

    private fun getDistance(it: Day14.Point, endPoint: Day14.Point) =
        abs(it.x - endPoint.x) + abs(it.y - endPoint.y)

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