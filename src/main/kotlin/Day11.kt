package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day11 : AdventCalendarDay("input_day11.txt") {


    abstract class Monkey (val ID : Int, val items : ArrayDeque<Item>){

        var inspected = 0L
        var modulo = 0L

        protected abstract fun operation(item : Item)
        abstract fun test()

        fun inspectAndOperate(){
            while(items.isNotEmpty()){
                inspected++
                operation(items.first())
                getsBored(items.first())
                test()
            }
        }

        private fun getsBored(item : Item){
            if(modulo == 0L){
                item.worryLevel /= 3
            }else{
                item.worryLevel = item.worryLevel % modulo
            }
        }

        fun throwTo(monkey : Monkey){
            monkey.items.addLast(items.removeFirst())
        }

        fun printItems() : String{
            var str = ""
            items.forEach { str += "${it.worryLevel} ,"}
            return str
        }
    }

    open class Monkey0 : Monkey(0, ArrayDeque(listOf(Item(99), Item(67)
                                                        , Item(92), Item(61)
                                                        , Item(83), Item(64)
                                                        , Item(98)))){
        override fun operation(item: Item) {
            item.worryLevel *= 17
        }

        override fun test() {
            if(items.first().worryLevel % 3 == 0L){
                throwTo(monkey4)
            }else{
                throwTo(monkey2)
            }
        }
    }

    open class Monkey1 : Monkey(1,ArrayDeque(listOf(Item(78), Item(74)
                                                        , Item(88), Item(89)
                                                        , Item(50)))){
        override fun operation(item: Item) {
            item.worryLevel *= 11
        }

        override fun test() {
            if(items.first().worryLevel % 5 == 0L){
                throwTo(monkey3)
            }else{
                throwTo(monkey5)
            }
        }
    }

    open class Monkey2 : Monkey(2, ArrayDeque(listOf(Item(98), Item(91)))){
        override fun operation(item: Item) {
            item.worryLevel += 4
        }

        override fun test() {
            if(items.first().worryLevel % 2 == 0L){
                throwTo(monkey6)
            }else{
                throwTo(monkey4)
            }
        }
    }

    open class Monkey3 : Monkey(3, ArrayDeque(listOf(Item(59), Item(72)
                                                        , Item(94), Item(91)
                                                        , Item(79), Item(88), Item(94)
                                                        , Item(51)))){
        override fun operation(item: Item) {
            item.worryLevel *= item.worryLevel
        }

        override fun test() {
            if(items.first().worryLevel % 13 == 0L){
                throwTo(monkey0)
            }else{
                throwTo(monkey5)
            }
        }
    }

    open class Monkey4 : Monkey(4, ArrayDeque(listOf(Item(95), Item(72), Item(78)))){
        override fun operation(item: Item) {
            item.worryLevel += 7
        }

        override fun test() {
            if(items.first().worryLevel % 11 == 0L){
                throwTo(monkey7)
            }else{
                throwTo(monkey6)
            }
        }
    }

    open class Monkey5 : Monkey(5, ArrayDeque(listOf(Item(76)))){
        override fun operation(item: Item) {
            item.worryLevel += 8
        }

        override fun test() {
            if(items.first().worryLevel % 17 == 0L){
                throwTo(monkey0)
            }else{
                throwTo(monkey2)
            }
        }
    }

    open class Monkey6 : Monkey(6, ArrayDeque(listOf(Item(69), Item(60)
                                                        , Item(53), Item(89)
                                                        , Item(71), Item(88)))){
        override fun operation(item: Item) {
            item.worryLevel += 5
        }

        override fun test() {
            if(items.first().worryLevel % 19 == 0L){
                throwTo(monkey7)
            }else{
                throwTo(monkey1)
            }
        }
    }

    open class Monkey7 : Monkey(7, ArrayDeque(listOf(Item(72), Item(54)
                                                        , Item(63), Item(80)))){
        override fun operation(item: Item) {
            item.worryLevel += 3
        }

        override fun test() {
            if(items.first().worryLevel % 7 == 0L){
                throwTo(monkey1)
            }else{
                throwTo(monkey3)
            }
        }
    }

    class Item(var worryLevel : Long){
        override fun toString(): String {
            return worryLevel.toString()
        }
    }

    override fun part1Impl(): Int {

        val monkeys = listOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)


        repeat(20) {
            monkeys.forEach {
                it.inspectAndOperate()
            }
        }

        val subList = monkeys.sortedByDescending { it.inspected }
            .take(2)

        return subList[0].inspected.toInt() * subList[1].inspected.toInt()
    }

    override fun part2Impl(): BigInteger {
        val monkeys = listOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)

        //compute modulo to reduce big number
        val modulo = listOf(3L, 5L, 2L, 13L, 11L, 17L, 19L, 7L).reduce(Long::times)

        monkeys.forEach { it.modulo = modulo }

        repeat(10000) {
            monkeys.forEach {
                it.inspectAndOperate()
            }
        }

        val subList = monkeys.sortedByDescending { it.inspected }
            .take(2)

        return subList[0].inspected.toBigInteger().multiply(subList[1].inspected.toBigInteger())
    }
}

object monkey0 : Day11.Monkey0()
object monkey1 : Day11.Monkey1()
object monkey2 : Day11.Monkey2()
object monkey3 : Day11.Monkey3()
object monkey4 : Day11.Monkey4()
object monkey5 : Day11.Monkey5()
object monkey6 : Day11.Monkey6()
object monkey7 : Day11.Monkey7()