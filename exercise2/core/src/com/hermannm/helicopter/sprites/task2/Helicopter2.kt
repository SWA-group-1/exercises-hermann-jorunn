package com.hermannm.helicopter.sprites.task2

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter2(x: Float, y: Float) {
    companion object {
        const val SPEED = 200F
    }
    val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(0F, 0F, 0F)
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, sprite.width, sprite.height)
    val sprite: Sprite = Sprite(Texture("attackhelicopter.PNG"))
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
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