package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day16 : AdventCalendarDay("input_day16.txt") {

    override fun part1Impl(): Int {
        val pathFinder = mutableMapOf<String, Set<String>>()

        val pressures = mutableMapOf<String, Int>()
        val paths = mutableMapOf<String, Set<String>>()

        inputLines.forEach {
            val valve = it.split(" ")[1]
            val pressure = it.split('=')[1].split(';')[0].toInt()
            val valves = mutableSetOf<String>()
            valves.addAll(it.replace(",","").split(" ").toList().subList(9, it.split(" ").size))
            pressures[valve] = pressure
            paths[valve] = valves
        }

        val visited = mutableSetOf<String>()
        //visited.add("AA")

        println("Building DFS")
        //build pathMap
        getAvailableNodesSorted(pressures, visited).filter { it.key == "AA" || it.value > 0 }.forEach { (first, _) ->
            getAvailableNodesSorted(pressures, visited).filter { it.value > 0 }.forEach {(second, _) ->
                if(first == second){
                    return@forEach
                }
                val possiblePaths = mutableListOf<Set<String>>()
                findPath(first, second, paths, mutableSetOf(), possiblePaths,10)
                if(possiblePaths.isNotEmpty()){
                    pathFinder[first+second]=possiblePaths.first()
                }
            }
        }

        pathFinder.any()

        val openedTimed = mutableMapOf<Int, Int>()
        /*openedTimed[20] = 3
        openedTimed[13] = 6
        openedTimed[21] = 10
        openedTimed[22] = 18
        openedTimed[3] = 22
        openedTimed[2] = 25*/

        println("Computing score")
        val interestingNodes = getAvailableNodesSorted(pressures, visited).filter { it.value > 0 }.keys
        val computedList = mutableSetOf<Int>()
        var max = 0
        var count = 100000000

        var maxTheoric = 0
        val maxs = pressures.toList().sortedBy { (_, value) -> value}.toMap().values.sortedDescending()
        maxs.forEachIndexed { i, v ->
            if(i > 15){
                return@forEachIndexed
            }
            maxTheoric += v * (30 - (i*2))
        }
        println("In theory max is $maxTheoric")
        val testList = listOf<String>("AH", "QE", "OQ", "VI", "QJ", "OS", "GJ", "EE", "SQ", "DV", "LU", "HY", "KU", "SB", "FF")
        while(count > 0) {
            openedTimed.clear()
            val tmp = mutableListOf<String>()
            //tmp.addAll(interestingNodes.shuffled())
            tmp.addAll(testList)
            if(!computedList.add(tmp.hashCode())){
                continue
            }
            tmp.add(0, "AA")
            var time = 0
            for(i in 0 until tmp.size-1){
                val src = tmp[i]
                val dest = tmp[i+1]
                if(pathFinder[src+dest] == null || pathFinder[src+dest]!!.isEmpty()){
                    break
                }
                time += pathFinder[src+dest]!!.size
                if(time >= 30){
                    break
                }
                openedTimed[pressures[dest]!!]=time
            }

            var tmpSum = 0
            openedTimed.forEach {
                tmpSum += it.key * (30 - it.value)
            }

            if(tmpSum > max){
                max = tmpSum
                println("New local max: $max with $tmp")
                count = 100000000
            }else{
                count--
            }
        }

        return max
    }

    private fun List<String>.permutations(): List<List<String>> {
        if (isEmpty()) return listOf(emptyList())
        return indices.fold(emptyList()) { result, i ->
            (result + (this - this[i])
                .permutations()
                .fold(mutableListOf()) { acc, item ->
                    acc.add(item + this[i])
                    acc
                }
                    ).toMutableList()
        }
    }

    private fun getAvailableNodesSorted(pressures : MutableMap<String, Int>, visited : Set<String>): MutableMap<String, Int> {
        val result = mutableMapOf<String, Int>()
        result.putAll(pressures)
        visited.forEach { result.remove(it)}
        result.toList().sortedBy { (_, value) -> value}.toMap()
        return result
    }

    private fun findPath(src: String, dest: String, paths: MutableMap<String, Set<String>>, visited: MutableSet<String>, possiblePaths: MutableList<Set<String>>, maxDepth: Int){
        visited.add(src)
        if(paths[src]!!.contains(dest)){
            val subVisited = mutableSetOf<String>()
            subVisited.addAll(visited)
            subVisited.add(dest)
            if(possiblePaths.isNotEmpty() && possiblePaths.first().size > subVisited.size){
                possiblePaths.removeAt(0)
            }
            possiblePaths.add(subVisited)
            return
        }
        for(tmp in paths[src]!!){
            if(visited.contains(tmp) || tmp == dest){
                continue
            }
            if(maxDepth == visited.size+1){
                continue
            }
            val subVisited = mutableSetOf<String>()
            subVisited.addAll(visited)
            findPath(tmp, dest, paths, subVisited, possiblePaths, maxDepth)
        }
    }


    override fun part2Impl(): BigInteger {
        TODO("Not yet implemented")
    }

}