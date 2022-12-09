package test

import org.junit.jupiter.api.Assertions.*
import src.main.kotlin.Day4

class Day4Test {

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(924, Day4().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(562, Day4().part1Impl())
    }
}