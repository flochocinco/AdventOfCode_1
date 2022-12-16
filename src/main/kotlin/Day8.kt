package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day8 : AdventCalendarDay("input_day8.txt") {
    override fun part1Impl(): Int {
        //iterate line per line and build sublist for left, right, top and down

        var count = 0
        for(j in inputLines.indices){
            if(j == 0 || j == inputLines.size-1){
                count += inputLines.size
                continue
            }
            val charArray = inputLines[j].toCharArray()
            for (i in charArray.indices){
                if(i == 0 || i == charArray.size-1){
                    count ++
                    continue
                }
                val topValues = mutableListOf<Char>()
                var tmp = 1
                while(j-tmp >= 0){
                    topValues.add(inputLines[j-tmp].toCharArray()[i])
                    tmp++
                }
                val bottomValues = mutableListOf<Char>()
                tmp = 1
                while(j+tmp < inputLines.size){
                    bottomValues.add(inputLines[j+tmp].toCharArray()[i])
                    tmp++
                }
                if(isVisible(charArray.slice(0 until i), charArray[i].digitToInt(), charArray.slice(i+1 until charArray.size)
                        , topValues
                        , bottomValues)) {
                    count++
                }
            }
        }
        return count
    }

    override fun part2Impl(): BigInteger {
        var maxScore = 1
        for(j in inputLines.indices){
            if(j == 0 || j == inputLines.size-1){
                continue
            }
            val charArray = inputLines[j].toCharArray()
            for (i in charArray.indices){
                if(i == 0 || i == charArray.size-1){
                    continue
                }
                val topValues = mutableListOf<Char>()
                var tmp = 1
                while(j-tmp >= 0){
                    topValues.add(inputLines[j-tmp].toCharArray()[i])
                    tmp++
                }
                val bottomValues = mutableListOf<Char>()
                tmp = 1
                while(j+tmp < inputLines.size) {
                    bottomValues.add(inputLines[j + tmp].toCharArray()[i])
                    tmp++
                }
                var score = 1
                score *= countVisibleTrees(charArray.slice(0 until i).reversed(), charArray[i].digitToInt())
                score *= countVisibleTrees(charArray.slice(i+1 until charArray.size), charArray[i].digitToInt())
                score *= countVisibleTrees(topValues, charArray[i].digitToInt())
                score *= countVisibleTrees(bottomValues, charArray[i].digitToInt())

                maxScore = maxScore.coerceAtLeast(score)
            }
        }
        return maxScore.toBigInteger()
    }

    private fun isVisible(leftValues : List<Char>, value : Int, rightValues: List<Char>, topValues : List<Char>, bottomValues : List<Char>) : Boolean {
        if(leftValues.all { it.digitToInt() < value }){
            return true
        }
        if(rightValues.all { it.digitToInt() < value }){
            return true
        }
        if(topValues.all { it.digitToInt() < value }){
            return true
        }
        if(bottomValues.all { it.digitToInt() < value }){
            return true
        }
        return false
    }

    private fun countVisibleTrees(trees : List<Char>, value: Int) : Int{
        var count = 0
        var tmp = 0
        while(tmp < trees.size){
            count++
            if(trees[tmp].digitToInt() >= value){
                return count
            }
            tmp++
        }
        return count
    }
}