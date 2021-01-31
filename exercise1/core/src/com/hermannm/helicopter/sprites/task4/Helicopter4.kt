package com.hermannm.helicopter.sprites.task4

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.hermannm.helicopter.sprites.task1.Helicopter1
import kotlin.math.abs

class Helicopter4(x: Float, y: Float) : Sprite() {
    companion object {
        const val SPEED = 200F
    }
    private var position: Vector3 = Vector3(x, y, 0F)
    private var yMultiplier: Float = 1F;
    private var velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    private var helicopter: Texture = Texture("attackhelicopter.PNG")
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
    //wallCollisions [top,bottom]
    //multipliers[x,y]
    fun changeVelocity(wallCollisions: Array<Boolean>, paddleCollision: Boolean, multipliers: Array<Float>){
        if (wallCollisions[0]) {
            velocity.y = -SPEED* abs(yMultiplier)
        }
        if (wallCollisions[1]) {
            velocity.y = SPEED*abs(yMultiplier)
        }
        if(paddleCollision){
            velocity.x = multipliers[0] * SPEED
            velocity.y = multipliers[1]* SPEED
            yMultiplier = multipliers[1]
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