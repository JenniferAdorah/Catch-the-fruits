package com.adorah.catchthefruits.models


class GameState {
    var score: Int = 0
    var lives: Int = 3
    var fruits: MutableList<Fruit> = mutableListOf()
    var basket: Basket = Basket()

    constructor(score: Int, lives: Int, fruits: MutableList<Fruit>, basket: Basket) {
        this.score = score
        this.lives = lives
        this.fruits = fruits
        this.basket = basket
    }

    constructor()

    fun addFruit(fruit: Fruit) {
        fruits.add(fruit)
    }

    fun removeFruit(fruit: Fruit) {
        fruits.remove(fruit)
    }

    fun updateGameState() {
        val iterator = fruits.iterator()
        while (iterator.hasNext()) {
            val fruit = iterator.next()
            fruit.y += fruit.speed

            if (basket.checkCollision(fruit)) {
                score += 10
                iterator.remove()
            } else if (fruit.y > 1000f) {
                lives -= 1
                iterator.remove()
            }
        }
    }

    fun gameOver(): Boolean {
        return lives <= 0
    }
}

private fun Basket.checkCollision(fruit: Fruit): Boolean {
                TODO("Not yet implemented")
}
