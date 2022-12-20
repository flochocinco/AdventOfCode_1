package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day16
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Day16Test {

    @OptIn(ExperimentalTime::class)
    @org.junit.jupiter.api.Test
    fun part1Impl() {
        val elapsed = measureTime {
            assertEquals(1728, Day16().part1Impl())
        }
        println("Computed in $elapsed")
    }

    @OptIn(ExperimentalTime::class)
    @org.junit.jupiter.api.Test
    fun part2Impl() {
        val elapsed = measureTime {
            assertEquals(2304, Day16().part2Impl().toInt())
        }
        println("Part 2 computed in $elapsed")
    }

}