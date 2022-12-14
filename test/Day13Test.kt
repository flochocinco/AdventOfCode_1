package test

import org.junit.jupiter.api.Assertions.*
import src.main.kotlin.Day13

class Day13Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(5938, Day13().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(29025, Day13().part2Impl())
    }

}