package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day17 : AdventCalendarDay("input_day17.txt") {
    private var pieceCounter = 0
    override fun part1Impl(): Int {
        val tower = Tower()

        var shape = getNextShape(0, 3)
        repeat(4){
            tower.createLine()
        }
        val numberOfInputs = inputLines[0].length
        var round = 0
        while(pieceCounter < 2022){
            val c = inputLines[0][round%numberOfInputs]
            if(c == '<'){
                shape.moveLeft(tower)
            }else if(c == '>'){
                shape.moveRight(tower)
            }
            if(!shape.canMoveDown(tower)){
                tower.addShape(shape)
                shape = getNextShape(++pieceCounter, tower.size()+3)
                repeat(shape.getHeight()+3){
                    tower.createLine()
                }
            }else{
                shape.moveDown()
                tower.removeUpperLineIfEmpty()
            }
            round++
        }

        println("Final State")
        tower.cleanEmptyLines()
        tower.print()

        return tower.size()
    }

    class Tower {
        val data = mutableListOf<Array<Int>>()
        fun createLine() {
            data.add(Array(7){0})
        }

        fun size() : Int{
            return data.size
        }

        fun addShape(shape : Shape){
            shape.points.forEach {
                data[it.y][it.x] = 1
            }
        }

        fun print(){
            data.reversed().forEachIndexed { idx, it ->
                print('|')
                it.forEach {pos ->  if(pos==0){print('.')}else{print('#') }}
                print('|')
                if(idx%10==0){
                    print(" ${data.size-idx}")
                }
                println()
            }
            println("+-------+")
        }

        fun cleanEmptyLines() {
            while(data.last().all { it == 0 }){
                data.removeLast()
            }
        }

        fun removeUpperLineIfEmpty() {
            if(data.last().all { it == 0 }){
                data.removeLast()
            }
        }
    }

    override fun part2Impl(): BigInteger {
        TODO("Not yet implemented")
    }

    private fun getNextShape(counter : Int, height : Int) : Shape{
        return when(counter%5){
            0 -> createHLine(height)
            1-> createStar(height)
            2 -> createL(height)
            3 -> createVLine(height)
            else -> createSquare(height)
        }
    }

    class Shape(val points : List<Day14.Point>){

        fun canMoveDown(tower: Tower): Boolean{
            return points.all { it.y > 0 } && points.all { tower.data[it.y-1][it.x] == 0 }
        }

        fun getHeight() : Int{
            return 1+points.maxOf { it.y } - points.minOf { it.y }
        }

        fun moveDown(){
            points.forEach {
                it.y = 0.coerceAtLeast(it.y-1)
            }
        }
        fun moveRight(tower: Tower){
            if(points.any { it.x == 6 }
                || points.any { tower.data[it.y][it.x+1] == 1}){
                return
            }
            points.forEach {
                it.x+=1
            }
        }
        fun moveLeft(tower: Tower){
            if(points.any { it.x == 0 }
                || points.any { tower.data[it.y][it.x-1] == 1}){
                return
            }
            points.forEach {
                it.x-=1
            }
        }
    }

    /**
     * ..@@@@.
     */
    private fun createHLine(lowestHeight : Int) : Shape{
        return Shape(listOf(Day14.Point(2,lowestHeight), Day14.Point(3,lowestHeight), Day14.Point(4,lowestHeight), Day14.Point(5,lowestHeight)))
    }

    /**
     *  |...@...|
     *  |..@@@..|
     *  |...@...|
     */
    private fun createStar(lowestHeight : Int) : Shape{
        return Shape(listOf(                                    Day14.Point(3,lowestHeight+2),
                            Day14.Point(2,lowestHeight+1), Day14.Point(3,lowestHeight+1), Day14.Point(4,lowestHeight+1)
                                                                , Day14.Point(3,lowestHeight)))
    }

    /**
     * |....@..|
     * |....@..|
     * |..@@@..|
     */
    private fun createL(lowestHeight : Int) : Shape{
        return Shape(listOf(                                                Day14.Point(4,lowestHeight+2),
                                                                            Day14.Point(4,lowestHeight+1),
            Day14.Point(2,lowestHeight), Day14.Point(3,lowestHeight), Day14.Point(4,lowestHeight)))
    }

    /**
     * |..@....|
     * |..@....|
     * |..@....|
     * |..@....|
     */
    private fun createVLine(lowestHeight : Int) : Shape{
        return Shape(listOf(Day14.Point(2,lowestHeight+3),
                            Day14.Point(2,lowestHeight+2),
                            Day14.Point(2,lowestHeight+1),
                            Day14.Point(2,lowestHeight)))
    }

    /**
     * |..@@...|
     * |..@@...
     */
    private fun createSquare(lowestHeight : Int) : Shape{
        return Shape(listOf(Day14.Point(2,lowestHeight+1), Day14.Point(3,lowestHeight+1),
            Day14.Point(2,lowestHeight), Day14.Point(3,lowestHeight)))
    }

}