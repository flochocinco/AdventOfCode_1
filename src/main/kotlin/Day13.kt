package src.main.kotlin

import AdventCalendarDay


@Suppress("UNCHECKED_CAST")
class Day13 : AdventCalendarDay("input_day13.txt") {

    override fun part1Impl(): Int {
        //var left = inputLines[0]
        //var right = inputLines[1]

        //var result = str.split(",").map { it.trim() }
        var res = 0
        //var count = BooleanArray(9)
        for(i in 0..inputLines.size step 3){
            val left = createList(inputLines[i].substring(1))
            val right = createList(inputLines[i+1].substring(1))
            if(compare(left, right) < 0){
                //supposed to false: 71, 78, 129
                //println(((i/3)+1))
                res+=((i/3)+1)
            }
        }

        /*for(i in count.indices){
            if(count[i]){
                res+=(i+1)
            }
            println("Pair " + (i+1) + " "+ count[i].toString())
        }*/

        return res
    }

    override fun part2Impl(): Int {
        //sort
        var lines = inputLines.toMutableList()
        lines.removeIf { it.isEmpty() }
        val dividerPacket1 = "[[2]]"
        val dividerPacket2 = "[[6]]"
        lines.add(dividerPacket1)
        lines.add(dividerPacket2)
        val packetComparator = Comparator { str1: String, str2: String -> compare(createList(str1.substring(1)), createList(str2.substring(1)))   }
        lines = lines.sortedWith( packetComparator).toMutableList()
        lines.forEach { println(it)}
        return (1+lines.indexOf(dividerPacket1)) * (1+lines.indexOf(dividerPacket2))
    }

    private fun createList(input : String) : List<Any> {
        val list = mutableListOf<Any>()
        var current =""
        var i = 0
        while ( i < input.length){
            when (input[i]) {
                ']' -> {
                    if(current.isEmpty()){
                        list.add(emptyList<Int>())
                    }else{
                        list.add(current.toInt())
                    }
                    return list
                }
                ',' -> {
                    if(current.isEmpty()){
                        list.add(emptyList<Int>())
                    }else{
                        list.add(current.toInt())
                    }
                    i++
                    current = ""
                }
                '[' -> {
                    val subList = createList(input.substring(i+1))
                    list.add(subList)
                    i += input.substring(i).indexOf("]") + 2/* ']' is always followed by ',' */
                }
                else -> {
                    current += input[i]
                    i++
                }
            }
        }

        return list
    }

    private fun compare(left : Any, right: Any) : Int{
        if(left is List<*> && right is List<*>){
            return compareLists(left as List<Int>, right as List<Int>)
        }
        if(left is Int){
            return compare(listOf(left), right)
        }
        if(right is Int){
            return compare(left, listOf(right))
        }

        return -1
    }

    /**
     * return
     */
    private fun compareLists(left : List<Any>, right : List<Any>) : Int{
        if(left.isEmpty()){
            return if(right.isNotEmpty()){
                -1
            }else{
                1
            }
        }
        if(right.isEmpty()){
            return 1
        }
        for ( i in left.indices){
            if(right.size <= i){
                return 1
            }
            if(left[i] is Int && right[i] is Int){
                if((left[i] as Int) < (right[i] as Int)){
                    return -1
                }
                if((left[i] as Int) > (right[i] as Int)){
                    return 1
                }
                continue
            }
            if(left[i] is List<*> && right[i] is List<*>){
                val leftList : List<Any> = left[i] as List<Any>
                val rightList : List<Any> = right[i] as List<Any>
                if(leftList == rightList){
                    continue
                }
            }
            return compare(left[i], right[i])
        }
        return -1
    }
}