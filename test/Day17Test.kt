package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day17
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Day17Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(3206, Day17().part1Impl())
    }

    @OptIn(ExperimentalTime::class)
    @org.junit.jupiter.api.Test
    fun part2Impl() {
        val elapsed = measureTime {
            //assertEquals(2304, Day16().part2Impl().toInt())
        }
        println("Part 2 computed in $elapsed")
    }

}