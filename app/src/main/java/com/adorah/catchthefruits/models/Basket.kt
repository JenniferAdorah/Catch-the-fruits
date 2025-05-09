package com.adorah.catchthefruits.models



class Basket {
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
    fun checkCollision(fruit: Fruit): Boolean {
        val fruitCenterX = fruit.x + 20f
        val fruitBottomY = fruit.y + 40f

        val basketTopY = this.y
        val basketBottomY = this.y + this.height
        val basketLeft = this.x
        val basketRight = this.x + this.width

        return fruitBottomY in basketTopY..basketBottomY &&
                fruitCenterX in basketLeft..basketRight
    }


    constructor()
}
