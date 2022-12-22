package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day12
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
class Day12Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        val elapsed = measureTime {
            assertEquals(380, Day12().part1Impl())
        }
        println("Computed in $elapsed")
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        val elapsed = measureTime {
            assertEquals(375.toBigInteger(), Day12().part2Impl())
        }
        println("Computed in $elapsed")
    }

}