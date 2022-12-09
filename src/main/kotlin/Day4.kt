package src.main.kotlin

import AdventCalendarDay

class Day4 : AdventCalendarDay("input_day4.txt") {
    /**
     * should return 924
     */
    override fun part2Impl(): String {
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
        return "Number of pairs overlapping the other: $pairs"
    }

    /**
     * should return 562
     */
    override fun part1Impl(): String {
        //split line in Range
        val pairs = inputLines.count {
            //it.split(",")[0].split("-")[0]
            val r1 = Range(
                it.split(",")[0].split("-")[0].toInt(), it.split(",")[0].split("-")[1].toInt()
            )
            val r2 = Range(
                it.split(",")[1].split("-")[0].toInt(), it.split(",")[1].split("-")[1].toInt()
            )
            r1.contains(r2)
        }
        return "Number of pairs fully containing the other: $pairs"
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