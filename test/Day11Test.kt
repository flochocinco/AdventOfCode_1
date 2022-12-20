package test

import org.junit.jupiter.api.Assertions.assertEquals
import src.main.kotlin.Day11
import java.math.BigInteger

class Day11Test {

    @org.junit.jupiter.api.Test
    fun part1Impl() {
        assertEquals(120384, Day11().part1Impl())
    }

    @org.junit.jupiter.api.Test
    fun part2Impl() {
        assertEquals(BigInteger("32059801242"), Day11().part2Impl())
    }

}