package com.marekpdev.shoppingapp.repository

import kotlin.random.Random

/**
 * Created by Marek Pszczolka on 03/03/2022.
 */
class Randomizer<T>(val items: List<T> = listOf()) {
    private var currentIndex = 0

    fun getRandomItems(): List<T> {
        val toReturn = mutableListOf<T>()

        items.forEachIndexed { index, item ->
            val match = Random(currentIndex++).nextBoolean()
            if(match){
                toReturn.add(item)
            }
        }

        if(toReturn.isEmpty()){
            toReturn.add(getRandomItem())
        }

        return toReturn.shuffled()
    }

    fun getRandomItem(): T {
        val randomIndex = Random(currentIndex++).nextInt(items.size)
        return items[randomIndex]
    }
}
