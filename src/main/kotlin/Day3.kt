
class Day3 : AdventCalendarDay("input_day3.txt") {

    /**
     * should return 7568
     */
    override fun part1Impl(): Int {
        val priorities = populateArray(inputLines)
        return computePrioritiesSum(priorities)
    }

    /**
     * should return 2780
     */
    override fun part2Impl(): Int {
        var part2Result = 0
        var tmp = 0
        while(tmp < inputLines.size){
            part2Result += getPriority(findCommonLetter(inputLines[tmp], inputLines[tmp+1], inputLines[tmp+2]))
            tmp+=3
        }
        return part2Result
    }

    /**
     * create an array where index is the value of the priority. The array value at specific index is the number of occurrence of one letter.
     *
     * eg: array[2] = 3 means that 'b' ('a' is 1) has been found 3 times
     */
    private fun populateArray(lines : List<String>) : IntArray{
        val priorities = IntArray(53) {0}
        lines.forEach {
            val index = getPriority(splitAndFindCommon(it))
            priorities[index] += 1
        }
        return priorities
    }

    private fun getPriority(letter : Char) : Int{
        return if(letter.code > 97){
            letter.code - 96
        }else{
            letter.code - 38
        }
    }

    private fun splitAndFindCommon(input: String): Char{
        return findCommon(input.substring(0, input.length/2), input.substring(input.length/2))
    }

    /**
     * find first letter present in 2 given Strings
     */
    private fun findCommon(input1 : String, input2 : String) : Char{
        return input1.first { input2.contains(it) }
    }

    private fun computePrioritiesSum(priorities : IntArray) : Int{
        var prioritiesSum = 0
        var index = 0
        while(++index < priorities.size){
            prioritiesSum += index * priorities[index]
        }
        return prioritiesSum
    }

    /**
     * find first letter present in 3 given Strings
     */
    private fun findCommonLetter(input1 : String, input2 : String, input3 : String) : Char {
        return input1.first { input2.contains(it) && input3.contains(it) }
    }
}