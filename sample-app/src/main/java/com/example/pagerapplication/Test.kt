package com.example.pagerapplication

annotation class Test {
    val CONST = 3
    fun main() {
        var numberLists = mutableListOf<Int>()

        numberLists.add(CONST * CONST)
        numberLists.add(CONST * 2)
        numberLists.add(CONST * 1)

        numberLists = mutableListOf<Int>()

        numberLists.add(CONST * 1)
        numberLists.add(CONST * 2)
        numberLists.add(CONST * CONST)

        println(numberLists.size)
    }
}