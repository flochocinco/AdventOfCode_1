package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day10

class Day10Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(15220, Day10().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        println("human shall be able to read \"RFZEKBFA\"")
        assertEquals(103.toBigInteger(), Day10().part2Impl())
    }

}