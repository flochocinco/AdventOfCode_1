package test

import Day3
import org.junit.jupiter.api.Assertions.*

class Day3Test {

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(2780, Day3().part2Impl())
    }

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(7568, Day3().part1Impl())
    }
}