package src.main.kotlin

import AdventCalendarDay
import java.util.SortedSet
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
        /*val set = mutableListOf<Int>()
        set.add(2)
        set.add(5)

        addInSet(set, 5,9)

        set.forEach { println("$it,") }*/

        val beacons = mutableListOf<Beacon>()
        var pct = 0
        for(i in 0 until 4000000){
            if(i%40000 == 0){
                pct++
                println("$pct%")
            }
            val notInLine10 = mutableListOf<Int>()

            inputLines.forEach {
                val sensorX = it.split("x=")[1].split(',')[0]
                val sensorY = it.split("y=")[1].split(':')[0]
                val beaconX = it.split("x=")[2].split(',')[0]
                val beaconY = it.split("y=")[2]

                val rayon = abs(sensorX.toInt()-beaconX.toInt()) + abs(sensorY.toInt()-beaconY.toInt())

                if(sensorY.toInt()+rayon >= i){
                    val x= 1+rayon- abs(i-sensorY.toInt())
                    val max = sensorX.toInt()+x.coerceAtMost(4000000)
                    val min = sensorX.toInt()-x.coerceAtLeast(0)

                    //beacons.add(Beacon(max, i))
                    //beacons.add(Beacon(min, i))

                    addInSet(notInLine10, min, max)


                }
            }

            if(notInLine10.size == 4){
                return i
            }

            /*if(notInLine10.size==4000000){
                for(x in 0 until 4000000){
                    if(!notInLine10.contains(x)){
                        println("x: $x, y=$i")
                        return x*4000000+i
                    }
                }
            }*/
        }

        return -1
    }

    private fun addInSet(mutableList: MutableList<Int>, min : Int, max : Int){
        if(mutableList.isEmpty()){
            mutableList.add(min)
            mutableList.add(max)
        }
        var idx = -1
        for(i in 0..mutableList.size-2 step 2){
            if(min < mutableList[i]){
                idx = i
                mutableList.add(i, min)
                break
            }
        }
        if(idx != -1 && idx <= mutableList.size){
            if(mutableList[idx+1] > max){
                mutableList.add(idx+1, max)
            }else{
                mutableList.removeAt(idx+1)
            }
        }else if(idx == -1){
            for(i in 1 until mutableList.size step 2){
                if(max > mutableList[i]){
                    if(mutableList[i] > min){
                        mutableList.removeAt(i)
                        mutableList.add(i, max)
                    }else{
                        mutableList.add(min)
                        mutableList.add(max)
                    }
                    break
                }
            }
        }
        //special case
        val intToRemove = mutableListOf<Int>()
        for(i in 1 until mutableList.size - 2){
            if(mutableList[i+1] - mutableList[i] <= 1){
                intToRemove.add(mutableList[i+1])
                intToRemove.add(mutableList[i])
            }
        }
        mutableList.removeAll(intToRemove)
    }
}