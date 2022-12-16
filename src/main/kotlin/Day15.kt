package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger
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

    override fun part2Impl(): BigInteger {
        val possibly = mutableSetOf<Int>()

        var pct = 0
        //feeling value is after 2000000 so we going decreasing order
        var i = 4000000
        while(i >= 0){
            if(i%400000 == 0 && i!=0){
                pct+=10
                println("$pct%")
            }
            val notInLine10 = mutableListOf<Int>()
            val maxList = mutableListOf<Int>()

            inputLines.forEach {
                val sensorX = it.split("x=")[1].split(',')[0]
                val sensorY = it.split("y=")[1].split(':')[0]
                val beaconX = it.split("x=")[2].split(',')[0]
                val beaconY = it.split("y=")[2]

                val rayon = abs(sensorX.toInt()-beaconX.toInt()) + abs(sensorY.toInt()-beaconY.toInt())

                if(sensorY.toInt()+rayon >= i){
                    val x= rayon- abs(i-sensorY.toInt())
                    if(x >= 0){
                        val max = (sensorX.toInt()+x).coerceAtMost(4000000)
                        val min = (sensorX.toInt()-x).coerceAtLeast(0)

                        //beacons.add(Beacon(max, i))
                        //beacons.add(Beacon(min, i))

                        addInSet(notInLine10, maxList, min, max)
                    }
                }
            }

            val holes = mutableSetOf<Int>()
            for(x in 0 until maxList.size){
                for(tmp in 0 until notInLine10.size){
                    if(notInLine10[tmp]-maxList[x]==2){
                        //println("Found at x: ${tmp-x} ; y:$i")
                        holes.add(notInLine10[tmp]-1)
                    }
                }
            }
            if(holes.size==1 && isInFreeSpace(notInLine10, maxList, holes.first())){
                println("Found at x: ${holes.first()} ; y:$i")
                val frequency : BigInteger = BigInteger("4000000").multiply(BigInteger(holes.first().toString())).add(BigInteger(i.toString()))
                println("frequency: $frequency")
                return frequency
            }
            i--
        }

        print("val possibly = listOf(")
        possibly.forEach { print("$it,")}
        print(")")

        return BigInteger("-1")
    }

    private fun isInFreeSpace(minList: MutableList<Int>, maxList: MutableList<Int>, value : Int) : Boolean {
        for(i in 0 until minList.size){
            if(minList[i] <= value && value <= maxList[i]){
                return false
            }
        }
        return true
    }

    private fun addInSet(minList: MutableList<Int>, maxList: MutableList<Int>, min : Int, max : Int){
        minList.add(min)
        maxList.add(max)
    }
}