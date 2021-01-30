package com.hermannm.helicopter.sprites.task1

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter1(x: Float, y: Float) : Sprite() {
    companion object {
        const val SPEED = 100F
    }
    private var position: Vector3 = Vector3(x, y, 0F)
    private var velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    private var helicopter: Texture = Texture("attackhelicopter.png")
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
            this.setFlip(false, false)
        }
        if (collisions[2]) {
            velocity.y = SPEED
        }
        if (collisions[3]) {
            velocity.x = SPEED
            this.setFlip(true, false)
        }
    }
    fun getPosition(): Vector3 {
        return position
    }
    override fun getTexture(): Texture {
        return helicopter
    }
    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, helicopter.getWidth().toFloat(), helicopter.getHeight().toFloat())
    }
}