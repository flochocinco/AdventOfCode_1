package src.main.kotlin

import AdventCalendarDay
import java.math.BigInteger

class Day7 : AdventCalendarDay("input_day7.txt") {

    var root = ElfFolder(null, "/")
    var folders = mutableListOf<ElfFolder>()

    override fun part1Impl(): Int {
        //create Root
        var currentFolder : ElfFolder? = root
        folders.add(root)
        inputLines.forEach {
            currentFolder = treatLine(it, currentFolder)
        }

        //folders.filter { it.getTotalSize() <=  100000 }.forEach { println("Total size of ${it.name} is ${it.getTotalSize()}") }
        return folders.filter { it.getTotalSize() <=  100000 }.sumOf { it.getTotalSize() }
    }

    override fun part2Impl(): BigInteger {

        //this method should be called after part1Impl
        if(root.files.isEmpty()){
            part1Impl()
        }

        val totalSpace = 70000000
        val requiredSpace = 30000000

        val usedSpace = root.getTotalSize()
        val freeSpace = totalSpace - usedSpace
        val minimalSizeToFind = requiredSpace - freeSpace

        //filter then find the min size
        var min = requiredSpace
        lateinit var candidate : ElfFolder
        folders
            .filter { it.getTotalSize() >= minimalSizeToFind }
            .forEach {
            if(min > it.getTotalSize()){
                min = it.getTotalSize()
                candidate = it
            }
        }

        return candidate.getTotalSize().toBigInteger()
    }

    fun treatLine(line: String, currentFolder: ElfFolder?) : ElfFolder? {
        if(currentFolder == null){
            return null
        }
        if(line.startsWith("$ cd")){
            return changeDirectory(currentFolder, line.replace("$ cd ", ""))
        }else if(line.contains("dir")){
            createFolder(currentFolder, line.replace("dir ", ""))
        }else if(line[0].isDigit()){
            createFile(currentFolder, line.split(" ")[1], line.split(" ")[0].toInt())
        }
        return currentFolder
    }

    fun createFolder(parent:ElfFolder, name: String){
        val folder = ElfFolder(parent, name)
        parent.folders.add(folder)
        folders.add(folder)
    }

    fun createFile(parent:ElfFolder, name: String, size: Int){
        val file = ElfFile(name, size, parent)
        parent.files.add(file)
    }

    fun changeDirectory(currentFolder : ElfFolder, name: String) : ElfFolder? {
        if("/".equals(name)){
            return root
        }else if("..".equals(name)){
            return currentFolder.parent
        }
        return currentFolder.folders.find { name.equals(it.name) }
    }

    class ElfFolder(val parent: ElfFolder?, val name: String){
        var files = mutableListOf<ElfFile>()
        var folders = mutableListOf<ElfFolder>()


        fun getTotalSize() : Int{
            return files.sumOf { it.getSize() } + folders.sumOf { it.getTotalSize() }
        }

        override fun toString(): String {
            var indent =""
            var tmp = parent
            while(tmp != null){
                indent += "  "
                tmp = tmp.parent
            }
            return "$indent - $name (dir)"
        }
    }

    class ElfFile(val name : String, private var size : Int, private var parent: ElfFolder){

        fun getSize(): Int {
            return size
        }

        override fun toString(): String {
            var indent =""
            var tmp : ElfFolder? = parent
            while(tmp != null){
                indent += "  "
                tmp = tmp.parent
            }
            return "$indent - $name (size: $size)"
        }
    }
}