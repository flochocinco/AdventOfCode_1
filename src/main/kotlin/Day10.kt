package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger
import kotlin.math.abs

class Day10 : AdventCalendarDay("input_day10.txt") {
    override fun part1Impl(): Int {
        val signalStrength = mutableListOf<Int>()
        var register = 1
        var counter = 0

        inputLines.forEach {
            when (it.split(" ")[0]) {
                "noop" -> counter = increaseCounter(counter, signalStrength, register)
                "addx" -> {
                    counter = increaseCounter(counter, signalStrength, register)
                    counter = increaseCounter(counter, signalStrength, register)
                    register += it.split(" ")[1].toInt()
                }
            }
        }

        return signalStrength.sum()
    }

    private fun increaseCounter(counter : Int, signalStrength : MutableList<Int>, register : Int) : Int{
        val newCounter = counter+1
        if(newCounter == 20 || (newCounter > 0 && newCounter%40 == 20)){
            signalStrength.add(register*newCounter)
        }
        return newCounter
    }

    private fun increaseCounterPart2(signalStrength : MutableList<Char>, register : Int, drawPosition : Int) {
        if(abs((drawPosition%40)-register) <= 1){
            signalStrength.add('#')
        }else{
            signalStrength.add('.')
        }
    }

    override fun part2Impl(): BigInteger {
        val display = mutableListOf<Char>()
        var register = 1
        var counter = 0
        var drawPosition = 0

        inputLines.forEach {
            when (it.split(" ")[0]) {
                "noop" -> increaseCounterPart2(display, register, drawPosition)
                "addx" -> {
                    increaseCounterPart2(display, register, drawPosition)
                    drawPosition++
                    counter++
                    increaseCounterPart2(display, register, drawPosition)
                    register += it.split(" ")[1].toInt()
                }
            }
            drawPosition+=1
            counter++
        }
        display.indices.forEach {
            if(it%40 == 0){
                println()
            }
            print(display[it])
        }
        println()
        //return number of # even if human shall be able to read
        return display.count { it == '#' }.toBigInteger()
    }
}