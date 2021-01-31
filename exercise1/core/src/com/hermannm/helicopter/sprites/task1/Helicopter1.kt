package com.hermannm.helicopter.sprites.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.hermannm.helicopter.sprites.task3.Helicopter3

class Helicopter1(x: Float, y: Float) {
    companion object {
        const val SPEED = 100F
    }
    val sprite = Sprite(Texture("attackhelicopter.PNG"))
    val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, sprite.width, sprite.height)
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun changeDirection(collisions: HashMap<String, Boolean>) {
        if (collisions["top"]!!) {
            velocity.y = -SPEED
        }
        if (collisions["right"]!!) {
            velocity.x = -SPEED
            sprite.setFlip(false, false)
        }
        if (collisions["bottom"]!!) {
            velocity.y = SPEED
        }
        if (collisions["left"]!!) {
            velocity.x = SPEED
            sprite.setFlip(true, false)
        }
    }
}