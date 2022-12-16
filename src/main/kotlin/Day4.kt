package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day4 : AdventCalendarDay("input_day4.txt") {
    /**
     * should return 924
     */
    override fun part2Impl(): BigInteger {
        //split line in Range
        val pairs = inputLines.count {
            //it.split(",")[0].split("-")[0]
            val r1 = Range(
                it.split(",")[0].split("-")[0].toInt(), it.split(",")[0].split("-")[1].toInt()
            )
            val r2 = Range(
                it.split(",")[1].split("-")[0].toInt(), it.split(",")[1].split("-")[1].toInt()
            )
            r1.overlaps(r2)
        }
        return pairs.toBigInteger()
    }

    /**
     * should return 562
     */
    override fun part1Impl(): Int {
        //split line in Range
        val pairs = inputLines.count {
            //it.split(",")[0].split("-")[0]
            val r1 = Range(
                it.split(",")[0].split("-")[0].toInt(), it.split(",")[0].split("-")[1].toInt()
            )
            val r2 = Range(
                it.split(",")[1].split("-")[0].toInt(), it.split(",")[1].split("-")[1].toInt()
            )
            r1.contains(r2) || r2.contains(r1)
        }
        return pairs
    }

     class Range constructor(private val min : Int, private val max : Int){
        fun contains(other : Range) : Boolean{
            return min <= other.min && max >= other.max
        }

        fun overlaps(other : Range) : Boolean {
            return other.min <= max && other.max >= min
        }

        override fun toString(): String {
            return "$min - $max"
        }
    }

}