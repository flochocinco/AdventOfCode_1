package src.main.kotlin

import AdventCalendarDay
import kotlin.math.abs

class Day15 : AdventCalendarDay("input_day15.txt") {
    override fun part1Impl(): Int {

        val notInLine10 = mutableSetOf<Int>()
        val beaconsAtLine = mutableSetOf<Beacon>()
        val lineToCheck = 2000000
        inputLines.forEach {
            val sensorX = it.split("x=")[1].split(',')[0]
            val sensorY = it.split("y=")[1].split(':')[0]
            val beaconX = it.split("x=")[2].split(',')[0]
            val beaconY = it.split("y=")[2]

            val rayon = abs(sensorX.toInt()-beaconX.toInt()) + abs(sensorY.toInt()-beaconY.toInt())

            if(sensorY.toInt()+rayon >= lineToCheck){
                for(x in 0 until 1+rayon- abs(lineToCheck-sensorY.toInt())){
                    notInLine10.add(sensorX.toInt()+x)
                    notInLine10.add(sensorX.toInt()-x)
                }
            }
            if(beaconY.toInt() == lineToCheck){
                beaconsAtLine.add(Beacon(beaconX.toInt(), beaconY.toInt()))
            }
        }

        return notInLine10.size-beaconsAtLine.size
    }

    class Beacon(val x: Int, val y : Int){
        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Beacon

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }
    }

    override fun part2Impl(): Int {
        TODO("Not yet implemented")
    }
}