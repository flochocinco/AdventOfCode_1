package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger
import kotlin.math.max

class Day16 : AdventCalendarDay("input_day16.txt") {

    override fun part1Impl(): Int {
        val opened = mutableListOf<Int>()
        var sum = 0

        val pressures = mutableMapOf<String, Int>()
        val paths = mutableMapOf<String, Set<String>>()

        //build maps for Dijsktra
        inputLines.forEach {
            val valve = it.split(" ")[1]
            val pressure = it.split('=')[1].split(';')[0].toInt()
            val valves = mutableSetOf<String>()
            valves.addAll(it.replace(",","").split(" ").toList().subList(9, it.split(" ").size))
            pressures[valve] = pressure
            paths[valve] = valves
        }

        var minutes = 0

        var visited = mutableSetOf<String>()
        visited.add("AA")

        while(minutes <= 30){
            println("${30-minutes} remaining")
            var nextNode = visited.last()
            var maxPotential = 0
            var minutesToRemove = 0
            getAvailableNodesSorted(pressures, visited).filter { it.value > 0 }.forEach { (k,v) ->
                var visited2 = mutableSetOf<String>()
                var possiblePaths = mutableListOf<Set<String>>()
                findPath(nextNode, k, paths, visited2, possiblePaths, 30-minutes)
                if(possiblePaths.isNotEmpty()){
                    //print("Point $k with pressure $v can be reached in ${possiblePaths.first().size} moves")
                    val potential = (30 - minutes - possiblePaths.first().size) * v
                    if(potential > maxPotential){
                        nextNode = k
                        maxPotential = potential
                        minutesToRemove = possiblePaths.first().size
                    }
                    //println(" - offering a potential of $potential")
                }else{
                    //println("Point $k with pressure $v cannot be reached in 10 moves")
                }
            }
            if(nextNode != visited.last()){
                println("Going to $nextNode spending $minutesToRemove")
                sum += maxPotential
                minutes+=minutesToRemove
                visited.add(nextNode)
            }else{
                break
            }
        }
        return sum
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
                possiblePaths.add(subVisited)
            }else if(possiblePaths.isEmpty()){
                possiblePaths.add(subVisited)
            }
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