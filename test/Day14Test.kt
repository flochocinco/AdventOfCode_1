package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day14

class Day14Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(964, Day14().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(32041, Day14().part2Impl())
    }

}