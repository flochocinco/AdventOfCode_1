package test

import org.junit.jupiter.api.Assertions.*
import src.main.kotlin.Day8

class Day8Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(1676, Day8().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(313200, Day8().part2Impl())
    }

}