package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger
import kotlin.math.abs

private const val START_SYMBOL = 'S'

private const val END_SYMBOL = 'E'

class Day12 : AdventCalendarDay("input_day12.txt") {

    class Edge(x: Int, y : Int) : Day14.Point(x,y){
        var distance = Int.MAX_VALUE
    }


    override fun part1Impl(): Int {
        var steps = Int.MAX_VALUE

        val map = mutableListOf<List<Int>>()

        inputLines.forEach {map.add(it.map { c -> getTranformedValue(c)})}

        val absoluteStartPosition = map.flatten().indexOf(getTranformedValue(START_SYMBOL))
        val startPoint = Edge(absoluteStartPosition%map[0].size, absoluteStartPosition/map[0].size)
        startPoint.distance=0
        val absoluteEndPosition = map.flatten().indexOf(getTranformedValue(END_SYMBOL))
        val endPoint = Edge(absoluteEndPosition%map[0].size, absoluteEndPosition/map[0].size)

        val pointToDistance = mutableMapOf<Edge, Int>()

        val path = mutableListOf<Edge>()
        path.add(startPoint)

        getPath(startPoint, endPoint, path, map, pointToDistance)

        //println("Shortest Path is: $pathSize to go from $startPoint to $endPoint")

        return pointToDistance[endPoint]!!
    }

    val leadingToVoid = mutableSetOf<Edge>()
    var counter = 0L
    private fun getPath( startPoint: Edge, endPoint: Edge, path: MutableList<Edge>, map: List<List<Int>>, pointToDistance: MutableMap<Edge, Int>    ){
        counter++
        println("${counter}: Current Chat value : ${map[startPoint.y][startPoint.x].toChar()} at point ${startPoint.x+1};${startPoint.y+1}, has a distance to start of: ${startPoint.distance}")
        //println("Path $path")
        var possibilities =
            listOf(getPointRight(startPoint), getPointUp(startPoint), getPointDown(startPoint), getPointLeft(startPoint))
                .filter { isLegal(startPoint, it, map) && !path.contains(it)}

        if(possibilities.isEmpty()) {
            return
        }
        if(possibilities.contains(endPoint)){
            //path.add(endPoint) as the path contains starting point, do not check in
            println("Found path of length: ${path.size}")
        }
        val currentDistance = startPoint.distance
        possibilities.forEach {
            //update distance
            if(pointToDistance.contains(it) && pointToDistance[it]!! <= currentDistance+1){
                return@forEach
            }
            pointToDistance[it] = currentDistance+1
            it.distance = currentDistance+1
            //recurse on possibilities
            path.add(it)
            getPath(it, endPoint, path, map, pointToDistance)
            path.remove(it)
        }
    }

    private fun getDistance(it: Edge, endPoint: Edge) =
        abs(it.x - endPoint.x) + abs(it.y - endPoint.y)

    private fun getTranformedValue(c : Char) : Int{
        return if (START_SYMBOL == c){
            'a'.code-1
        }else if(END_SYMBOL == c){
            'z'.code+1
        }else{c.code }
    }

    private fun getPointUp(startPoint: Edge) : Edge{
        return Edge(startPoint.x , startPoint.y-1)
    }

    private fun getPointDown(startPoint: Edge) : Edge{
        return Edge(startPoint.x , startPoint.y+1)
    }

    private fun getPointRight(startPoint: Edge) : Edge{
        return Edge(startPoint.x+1 , startPoint.y)
    }

    private fun getPointLeft(startPoint: Edge) : Edge{
        return Edge(startPoint.x-1 , startPoint.y)
    }

    private fun isLegal(startPoint: Edge, endPoint: Edge, map : List<List<Int>>) : Boolean {
        return endPoint.x >= 0 && endPoint.y >= 0 && endPoint.x < map[0].size && endPoint.y < map.size
                && map[endPoint.y][endPoint.x] - map[startPoint.y][startPoint.x] <= 1 /* at most 1 if climbing or anything if descending*/
    }

    override fun part2Impl(): BigInteger {
        TODO("Not yet implemented")
    }
}