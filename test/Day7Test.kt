package test

import org.junit.jupiter.api.Assertions.*
import src.main.kotlin.Day7

class Day7Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(1582412, Day7().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(3696336, Day7().part2Impl())
    }

}