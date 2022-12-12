package src.main.kotlin

import AdventCalendarDay
import kotlin.math.abs

class Day9 : AdventCalendarDay("input_day9.txt") {
    override fun part1Impl(): Int {
        val position = mutableSetOf<Position>()

        var hx = 0
        var hy = 0
        var tx = 0
        var ty = 0

        inputLines.forEach {
            val nb = it.split(" ")[1]
            val direction = it.split(" ")[0]

            repeat(nb.toInt()){
                when (direction) {
                    "D" -> hy--
                    "U" -> hy++
                    "R" -> hx++
                    "L" -> hx--
                }
                if (needToMove(hx, hy, tx, ty)){
                    when (direction) {
                        "D" -> {
                            ty = hy + 1
                            tx = hx
                        }
                        "U" -> {
                            ty = hy - 1
                            tx = hx
                        }
                        "R" -> {
                            ty = hy
                            tx = hx-1
                        }
                        "L" -> {
                            ty = hy
                            tx = hx+1
                        }
                    }
                }
                position.add(Position(tx, ty))
            }
        }

        return position.size
    }

    override fun part2Impl(): Int {
        TODO("Not yet implemented")
    }

    class Position(val x : Int, val y : Int) {
        override fun equals(other: Any?): Boolean {
            if(other is Position){
                return other.x == x && other.y == y
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }

    private fun needToMove(hx : Int, hy : Int, tx : Int, ty : Int) : Boolean{
        return abs(hx-tx) > 1 || abs(hy-ty) > 1
    }

}