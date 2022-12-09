package test

import Day1
import org.junit.jupiter.api.Assertions.*

class Day1Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(71471, Day1().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(211189, Day1().part2Impl())
    }
}