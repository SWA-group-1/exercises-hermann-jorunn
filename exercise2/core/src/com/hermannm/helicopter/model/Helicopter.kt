package com.hermannm.helicopter.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Helicopter(x: Float, y: Float) {
    companion object {
        const val SPEED = 200f
    }
    val height = 264
    val width = 109
    val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2 = Vector2(0f, 0f)
    val sprite: Sprite = Sprite(Texture("attackhelicopter.PNG"))
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y)
        velocity.scl(1/deltaTime)
    }
    // xMultiplier: 1 / -1 / 0, yMultiplier: 1 / -1 / 0
    // collisions: { top, right, bottom, left }
    fun control(xMultiplier: Int, yMultiplier: Int, collisions: HashMap<String, Boolean>) {
        var x = xMultiplier
        var y = yMultiplier
        if (yMultiplier == 1 && collisions["top"]!!) {
            y = 0
        } else if (yMultiplier == -1 && collisions["bottom"]!!) {
            y = 0
        }
        if (xMultiplier == 1) {
            sprite.setFlip(true, false)
            if (collisions["right"]!!) {
                x = 0
            }
        } else if (xMultiplier == -1) {
            sprite.setFlip(false, false)
            if (collisions["left"]!!) {
                x = 0
            }
        }
        velocity.x = SPEED * x
        velocity.y = SPEED * y
    }
}