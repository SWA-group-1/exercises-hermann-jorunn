package com.hermannm.helicopter.sprites.task2

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter2(x: Float, y: Float) {
    companion object {
        const val SPEED = 200F
    }
    private var position: Vector3 = Vector3(x, y, 0F)
    private var velocity: Vector3 = Vector3(0F, 0F, 0F)
    private var chopper: Sprite = Sprite(Texture("attackhelicopter.PNG"))
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    // xMultiplier: 1 / -1 / 0, yMultiplier: 1 / -1 / 0
    // collisions: [top, right, bottom, left]
    fun control(xMultiplier: Int, yMultiplier: Int, collisions: Array<Boolean>) {
        var x = xMultiplier
        var y = yMultiplier
        if (yMultiplier == 1 && collisions[0]) {
            y = 0
        } else if (yMultiplier == -1 && collisions[2]) {
            y = 0
        }
        if (xMultiplier == 1) {
            chopper.setFlip(true, false)
            if (collisions[1]) {
                x = 0
            }
        } else if (xMultiplier == -1) {
            chopper.setFlip(false, false)
            if (collisions[3]) {
                x = 0
            }
        }
        velocity.x = SPEED * x
        velocity.y = SPEED * y
    }
    fun getPosition(): Vector3 {
        return position
    }
    fun getSprite(): Sprite {
        return chopper
    }
    fun getTexture(): Texture {
        return chopper.getTexture()
    }
    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, chopper.getWidth().toFloat(), chopper.getHeight().toFloat())
    }
}