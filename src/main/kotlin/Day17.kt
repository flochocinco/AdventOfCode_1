package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day17 : AdventCalendarDay("input_day17.txt") {
    private var pieceCounter = 0
    override fun part1Impl(): Int {
        val tower = mutableListOf<Array<Int>>()

        var nextShape = getNextShape(0, 3)
        repeat(4){
            tower.add(createLine())
        }
        val numberOfInputs = inputLines[0].length
        var round = 0
        while(pieceCounter < 2022){
            val c = inputLines[0][round%numberOfInputs]
            if(c == '<'){
                nextShape.moveLeft(tower)
            }else if(c == '>'){
                nextShape.moveRight(tower)
            }
            if(!nextShape.canMoveDown(tower)){
                addInTower(nextShape, tower)
                nextShape = getNextShape(++pieceCounter, tower.size+3)
                repeat(nextShape.getHeight()+3){
                    tower.add(createLine())
                }
            }else{
                nextShape.moveDown()
                if(tower.last().all { it == 0 }){
                    tower.removeLast()
                }
            }
            round++
        }

        println("Final State")

        tower.removeIf { it.all { place -> place == 0 } }
        printTower(tower)

        return tower.size
    }

    private fun createLine(): Array<Int> {
        return Array(7){0}
    }

    private fun addInTower(shape : Shape, tower : List<Array<Int>>){
        shape.points.forEach {
            tower[it.y][it.x] = 1
        }
    }

    private fun printTower(tower : List<Array<Int>>){
        tower.reversed().forEachIndexed { idx, it ->
            print('|')
            it.forEach {pos ->  if(pos==0){print('.')}else{print('#') }}
            print('|')
            if(idx%10==0){
                print(" ${tower.size-idx}")
            }
            println()
        }
        println("+-------+")
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

        fun canMoveDown(tower: MutableList<Array<Int>>): Boolean{
            return points.all { it.y > 0 } && points.all { tower[it.y-1][it.x] == 0 }
        }

        fun getHeight() : Int{
            return 1+points.maxOf { it.y } - points.minOf { it.y }
        }

        fun moveDown(){
            points.forEach {
                it.y = 0.coerceAtLeast(it.y-1)
            }
        }
        fun moveRight(tower: MutableList<Array<Int>>){
            if(points.any { it.x == 6 }
                || points.any { tower[it.y][it.x+1] == 1}){
                return
            }
            points.forEach {
                it.x+=1
            }
        }
        fun moveLeft(tower: MutableList<Array<Int>>){
            if(points.any { it.x == 0 }
                || points.any { tower[it.y][it.x-1] == 1}){
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