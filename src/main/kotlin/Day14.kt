package src.main.kotlin

import AdventCalendarDay

class Day14 : AdventCalendarDay("input_day14.txt") {
    val endlessVoidTrigger = 200
    override fun part1Impl(): Int {
        val occupied = mutableSetOf<Point>()

        inputLines.forEach{ fill(occupied, it)}

        var sandCount = 0
        while(sandCount < 10000 /* guard */){
            val place = fallSand(occupied, Point(500, 0))
            if(place.y > endlessVoidTrigger){
                return sandCount
            }
            occupied.add(place)
            sandCount++
        }

        return occupied.size
    }

    private fun fallSand(occupied: MutableSet<Point>, start : Point) : Point{
        var place = fallDown(occupied, start)
        if(place.y > endlessVoidTrigger){
            return place
        }
        //down left ?
        val fallDownLeft = fallDownLeft(occupied, place)
        if(fallDownLeft != place){
            return fallSand(occupied, fallDownLeft)
        }
        val fallDownRight = fallDownRight(occupied, place)
        if(fallDownRight != place){
            return fallSand(occupied, fallDownRight)
        }
        return place
    }

    private fun fallDown(occupied: MutableSet<Point>, start : Point) : Point{
        var nextPoint = start
        while(!occupied.contains(nextPoint)){
            nextPoint = Point(nextPoint.x, nextPoint.y+1)
            if(nextPoint.y > endlessVoidTrigger){
                return nextPoint
            }
        }
        return Point(nextPoint.x, nextPoint.y-1)
    }

    private fun fallDownLeft(occupied: MutableSet<Point>, start : Point) : Point{
        var nextPoint = Point(start.x-1, start.y+1)
        if(!occupied.contains(nextPoint)){
            return nextPoint
        }
        return start
    }

    private fun fallDownRight(occupied: MutableSet<Point>, start : Point) : Point{
        var nextPoint = Point(start.x+1, start.y+1)
        if(!occupied.contains(nextPoint)){
            return nextPoint
        }
        return start
    }

    private fun fill(occupied: MutableSet<Point>, input: String) {
        val point = input.split(" -> ")
        for(i in 0 until point.size-1){
            val start = Point(point[i].split(',')[0].toInt(), point[i].split(',')[1].toInt())
            val to = Point(point[i+1].split(',')[0].toInt(), point[i+1].split(',')[1].toInt())
            storeLine(occupied, start, to)
        }
        point.size
    }

    private fun storeLine(occupied: MutableSet<Point>, start : Point, to : Point){
        occupied.add(start)
        if(start.x == to.x){
            val coef = if(start.y > to.y){
                -1
            }else{
                1
            }
            var tmp = coef
            while (kotlin.math.abs(start.y + tmp - to.y) != 0){
                occupied.add(Point(start.x, start.y+tmp))
                tmp += coef
            }
        }else if(start.y == to.y){
            val coef = if(start.x > to.x){
                -1
            }else{
                1
            }
            var tmp = coef
            while (kotlin.math.abs(start.x + tmp - to.x) != 0){
                occupied.add(Point(start.x+tmp, start.y))
                tmp += coef
            }
        }
        occupied.add(to)
    }

    class Point(val x : Int, val y : Int){
        override fun toString(): String {
            return "x: $x ; y: $y"
        }

        override fun equals(other: Any?): Boolean {
            if(other is Point){
                return other.x == x && other.y == y
            }
            return false
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }

    override fun part2Impl(): Int {
        TODO("Not yet implemented")
    }
}