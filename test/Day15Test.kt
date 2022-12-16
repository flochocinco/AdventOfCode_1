package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day15
import java.math.BigInteger
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Day15Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(5112034, Day15().part1Impl())
    }

    @OptIn(ExperimentalTime::class)
    @org.junit.jupiter.api.Test
    fun part2Impl() {
        val elapsed = measureTime {
            assertEquals(BigInteger("13172087230812"), Day15().part2Impl())
        }
        println("Computed in $elapsed")
    }

}