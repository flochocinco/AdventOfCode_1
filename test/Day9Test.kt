package test

import org.junit.jupiter.api.Assertions.*
import src.main.kotlin.Day9

class Day9Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(6470, Day9().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(2658, Day9().part2Impl())
    }

}