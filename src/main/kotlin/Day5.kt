import java.math.BigInteger

class Day5 : AdventCalendarDay("input_day5.txt"){
    override fun part1Impl(): Int {
        initStack()
        inputLines.forEach {
            val nb = it.split(" ")[1].toInt()
            val from = it.split(" ")[3].toInt() - 1 //I start from index 0 while input starts from 1
            val to = it.split(" ")[5].toInt() - 1
            move(nb, from, to)
        }
        print("Last element of each stack: ")
        printStacks()
        println()
        return 0
    }

    override fun part2Impl(): BigInteger {
        initStack()
        inputLines.forEach {
            val nb = it.split(" ")[1].toInt()
            val from = it.split(" ")[3].toInt() - 1 //I start from index 0 while input starts from 1
            val to = it.split(" ")[5].toInt() - 1
            moveAndKeepOrder(nb, from, to)
        }
        print("Last element of each stack: ")
        printStacks()
        println()
        return 0.toBigInteger()
    }

    private var a = ArrayDeque<Char>()
    private var b = ArrayDeque<Char>()
    private var c = ArrayDeque<Char>()
    private var d = ArrayDeque<Char>()
    private var e = ArrayDeque<Char>()
    private var f = ArrayDeque<Char>()
    private var g = ArrayDeque<Char>()
    private var h = ArrayDeque<Char>()
    private var i = ArrayDeque<Char>()

    private var tmpStack = ArrayDeque<Char>()

    private fun initStack() {
        a = ArrayDeque(listOf('H', 'C', 'R'))
        b = ArrayDeque(listOf('B', 'J', 'H', 'L', 'S', 'F'))
        c = ArrayDeque(listOf('R', 'M', 'D', 'H', 'J', 'T', 'Q'))
        d = ArrayDeque(listOf('S', 'G', 'R', 'H', 'Z', 'B', 'J'))
        e = ArrayDeque(listOf('R', 'P', 'F', 'Z', 'T', 'D', 'C', 'B'))
        f = ArrayDeque(listOf('T', 'H', 'C', 'G'))
        g = ArrayDeque(listOf('S', 'N', 'V', 'Z', 'B', 'P', 'W', 'L'))
        h = ArrayDeque(listOf('R', 'J', 'Q', 'G', 'C'))
        i = ArrayDeque(listOf('L', 'D', 'T', 'R', 'H', 'P', 'F', 'S'))
    }

    private fun move(nb : Int, from : Int, to : Int) {
        val fromStack = getStack(from)
        val toStack = getStack(to)

        repeat(nb) {
            toStack.add(fromStack.removeLast())
        }
    }

    private fun moveAndKeepOrder(nb : Int, from : Int, to : Int) {
        val fromStack = getStack(from)
        val toStack = getStack(to)

        repeat(nb) {
            tmpStack.add(fromStack.removeLast())
        }
        repeat(nb) {
            toStack.add(tmpStack.removeLast())
        }
    }

    private fun printStacks() {
        repeat(9) { index -> print(getStack(index).last()) }
    }

    private fun getStack(index : Int) : ArrayDeque<Char> {
        return when (index) {
            0 -> {
                a
            }
            1 -> {
                b
            }
            2 -> {
                c
            }
            3 -> {
                d
            }
            4 -> {
                e
            }
            5 -> {
                f
            }
            6 -> {
                g
            }
            7 -> {
                h
            }
            else -> {
                i
            }
        }
    }

}