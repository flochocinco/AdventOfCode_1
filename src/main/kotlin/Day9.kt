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
        val position = mutableSetOf<Position>()


        //init rope
        val rope = mutableListOf<Position>()
        for(i in 0..10){
            rope.add(Position(0,0))
        }

        inputLines.forEach {
            val nb = it.split(" ")[1]
            val direction = it.split(" ")[0]

            repeat(nb.toInt()){
                when (direction) {
                    "D" -> rope[0].moveDown()
                    "U" -> rope[0].moveUp()
                    "R" -> rope[0].moveRight()
                    "L" -> rope[0].moveLeft()
                }
                for(i in 1..9){
                    if (needToMove(rope[i-1].x, rope[i-1].y, rope[i].x, rope[i].y)){
                        rope[i].follow(rope[i-1])
                    }
                }
                position.add(Position(rope[9].x, rope[9].y))
            }
        }

        return position.size
    }

    class Position(var x : Int, var y : Int) {
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

        fun follow(leader : Position){
            if(leader.x == x){
                if(leader.y > y){
                    y+=1
                }else{
                    y-=1
                }
                return
            }
            if(leader.y == y){
                if(leader.x > x){
                    x+=1
                }else{
                    x-=1
                }
                return
            }
            if(leader.y > y){
                y+=1
            }else{
                y-=1
            }
            if(leader.x > x){
                x+=1
            }else{
                x-=1
            }
        }

        fun moveDown(){
            y-=1
        }
        fun moveUp(){
            y+=1
        }
        fun moveRight(){
            x+=1
        }
        fun moveLeft(){
            x-=1
        }
    }

    private fun needToMove(hx : Int, hy : Int, tx : Int, ty : Int) : Boolean{
        return abs(hx-tx) > 1 || abs(hy-ty) > 1
    }

}