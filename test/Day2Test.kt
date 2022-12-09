package test

import org.junit.jupiter.api.Assertions.*
import src.main.kotlin.Day2

class Day2Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(12276, Day2().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(9975, Day2().part2Impl())
    }
}