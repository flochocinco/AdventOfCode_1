package src.main.kotlin

import AdventCalendarDay

class Day2 : AdventCalendarDay("input_day2.txt") {
    private val drawPoints = 3
    private val victoryPoints = 6

    override fun part2Impl(): Int {
        var pts = 0
        for(line in inputLines){
            pts += if(line.contains("X")){
                makeLose(line)
            }else if(line.contains("Y")){
                makeDraw(line)
            }else {
                makeWin(line)
            }
        }

        return pts
    }

    override fun part1Impl(): Int {
        return computePoints(inputLines)
    }

    private fun computePoints(lines : List<String>) : Int{

        var points: Int = lines.count { it.contains("X") }
        points += 2 * lines.count { it.contains("Y") }
        points += 3 * lines.count { it.contains("Z") }
        points += drawPoints * lines.count { it.contains("A X") ||  it.contains("B Y") || it.contains("C Z") }
        points += victoryPoints * lines.count { it.contains("A Y")  ||  it.contains("B Z") || it.contains("C X") }
        return points
    }

    /**
     * compute point in order to lose -> compute point of played figure
     */
    private fun makeLose(input : String) : Int {
        return if(input.contains('A')){
            3
        }else if(input.contains('B')){
            1
        }else {
            2
        }
    }

    /**
     * compute point in order to draw -> 3 + compute point of played figure
     */
    private fun makeDraw(input : String) : Int {
        var pts = drawPoints
        pts += if(input.contains('A')){
            1
        }else if(input.contains('B')){
            2
        }else {
            3
        }
        return pts
    }


    /**
     * compute point in order to win -> 6 + compute point of played figure
     */
    private fun makeWin(input : String) : Int {
        var pts = victoryPoints
        pts += if(input.contains('A')){
            2
        }else if(input.contains('B')){
            3
        }else {
            1
        }
        return pts
    }
}