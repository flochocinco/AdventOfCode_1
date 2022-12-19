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
            assertEquals(1651, Day16().part1Impl())
        }
        println("Computed in $elapsed")
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        //assertEquals(32041, Day15().part2Impl())
    }

}