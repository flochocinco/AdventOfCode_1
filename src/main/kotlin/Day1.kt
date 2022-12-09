
class Day1 : AdventCalendarDay("input.txt") {
    override fun part2Impl(): Int {
        var amounts = mutableListOf<Int>()
        var tmp = 0

        for(line in inputLines){
            if(line.isEmpty()){
                amounts.add(tmp)
                tmp = 0
            }else{
                tmp += Integer.valueOf(line)
            }
        }

        amounts.sort()
        amounts.reverse()
        amounts = amounts.subList(0, 3)//only get 1st item for part 1
        return amounts.sum()
    }

    override fun part1Impl(): Int {
        TODO("Not yet implemented")
    }
}