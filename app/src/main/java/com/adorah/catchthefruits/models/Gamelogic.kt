package com.adorah.catchthefruits.models


class Gamelogic {
    var x: Float = 0f
    var y: Float = 0f
    var width: Float = 100f
    var height: Float = 50f
    var speed: Float = 10f

    constructor(x: Float, y: Float, width: Float, height: Float, speed: Float) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
        this.speed = speed
    }

    constructor()

    fun moveLeft() {
        x -= speed
    }

    fun moveRight() {
        x += speed
    }

    fun checkCollision(fruit: Fruit): Boolean {
        return fruit.x + 30f > x && fruit.x < x + width &&
                fruit.y + 30f > y && fruit.y < y + height
    }
}
