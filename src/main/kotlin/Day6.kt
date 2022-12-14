import java.math.BigInteger

class Day6 : AdventCalendarDay("input_day6.txt") {
    override fun part1Impl(): Int {
        return findPatternOfNChar(4).toInt()
    }

    override fun part2Impl(): BigInteger {
        return findPatternOfNChar(14)
    }

    private fun findPatternOfNChar(n : Int) : BigInteger{
        val input = inputLines[0]
        val markerFinder = PatternFinder(n)
        var index = 0
        while(!markerFinder.add(input[index])){
            index++
        }
        return (index+1).toBigInteger()
    }

    class PatternFinder (private val markerLength : Int ){
        var set = ArrayDeque<Char>(listOf())

        /**
         * return true if the Deque contains [markerLength] unique Char
         */
        fun add(element: Char): Boolean {
            set.add(element)
            if(set.size > markerLength){
                set.removeFirst()
            }
            return set.size == markerLength && set.distinct().size == set.size
        }
    }


}