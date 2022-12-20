package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day11 : AdventCalendarDay("input_day11.txt") {


    abstract class Monkey (val ID : Int, val items : ArrayDeque<Item>){

        protected abstract fun operation(item : Item)
        abstract fun test()

        public fun inspectAndOperate(){
            while(items.isNotEmpty()){
                operation(items.first())
                getsBored(items.first())
                test()
            }
        }

        private fun getsBored(item : Item){
            item.worryLevel /= 3
        }

        public fun throwTo(monkey : Monkey){
            monkey.items.addLast(items.removeFirst())
        }
    }

    open class Monkey0 : Monkey(0, ArrayDeque(listOf(Item(79), Item(98)))){
        override fun operation(item: Item) {
            item.worryLevel *= 19
        }

        override fun test() {
            if(items.first().worryLevel % 23 == 0){
                throwTo(monkey2)
            }else{
                throwTo(monkey3)
            }
        }
    }

    open class Monkey1 : Monkey(1,ArrayDeque(listOf(Item(54), Item(65), Item(75), Item(74)))){
        override fun operation(item: Item) {
            item.worryLevel += 6
        }

        override fun test() {
            if(items.first().worryLevel % 19 == 0){
                throwTo(monkey2)
            }else{
                throwTo(monkey0)
            }
        }
    }

    open class Monkey2 : Monkey(2, ArrayDeque(listOf(Item(79), Item(60), Item(97)))){
        override fun operation(item: Item) {
            item.worryLevel *= item.worryLevel
        }

        override fun test() {
            if(items.first().worryLevel % 13 == 0){
                throwTo(monkey1)
            }else{
                throwTo(monkey3)
            }
        }
    }

    open class Monkey3 : Monkey(3, ArrayDeque(listOf(Item(74)))){
        override fun operation(item: Item) {
            item.worryLevel += 3
        }

        override fun test() {
            if(items.first().worryLevel % 17 == 0){
                throwTo(monkey0)
            }else{
                throwTo(monkey1)
            }
        }
    }

    class Item(var worryLevel : Int)

    override fun part1Impl(): Int {
        monkey0.inspectAndOperate()
        monkey1.inspectAndOperate()
        monkey2.inspectAndOperate()
        monkey3.inspectAndOperate()

        return 0
    }

    override fun part2Impl(): BigInteger {
        TODO("Not yet implemented")
    }
}

object monkey0 : Day11.Monkey0()
object monkey1 : Day11.Monkey1()
object monkey2 : Day11.Monkey2()
object monkey3 : Day11.Monkey3()
