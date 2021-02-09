package com.hermannm.helicopter.model

import com.badlogic.gdx.math.Vector2

class Helicopter(x: Float, y: Float) {
    companion object {
        const val SPEED = 200f
    }
    val height = 264
    val width = 109
    val position: Vector2 = Vector2(x, y)
    val velocity: Vector2 = Vector2(0f, 0f)
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y)
        velocity.scl(1/deltaTime)
    }
    // xMultiplier: 1 / -1 / 0, yMultiplier: 1 / -1 / 0
    fun control(xMultiplier: Int, yMultiplier: Int) {
        velocity.x = SPEED * xMultiplier
        velocity.y = SPEED * yMultiplier
    }
}