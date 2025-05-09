package com.adorah.catchthefruits.models

enum class Fruitype {
    APPLE,BANANA,ORANGE,PAIR,GUAVA,STRAWBERRY
}

class Fruit {
    var x: Float = 0f
    var y: Float = 0f
    var speed: Float = 0f
    var type: Fruitype = Fruitype.APPLE

    constructor(x: Float, y: Float, speed: Float, type: Fruitype) {
        this.x = x
        this.y = y
        this.speed = speed
        this.type = type
    }

constructor()}