package com.hermannm.helicopter.sprites.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter1(x: Float, y: Float) : Sprite(Texture("attackhelicopter.PNG")) {
    companion object {
        const val SPEED = 100F
    }
    private val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun changeDirection(collisions: Array<Boolean>) {
        if (collisions[0]) {
            velocity.y = -SPEED
        }
        if (collisions[1]) {
            velocity.x = -SPEED
            setFlip(false, false)
        }
        if (collisions[2]) {
            velocity.y = SPEED
        }
        if (collisions[3]) {
            velocity.x = SPEED
            setFlip(true, false)
        }
    }
    fun getPosition(): Vector3 {
        return position
    }
    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, this.getWidth(), this.getHeight())
    }
}