package com.hermannm.helicopter.sprites.task4

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.hermannm.helicopter.sprites.task1.Helicopter1
import kotlin.math.abs

class Helicopter4(x: Float, y: Float) {
    companion object {
        const val SPEED = 300F
    }
    var position: Vector3 = Vector3(x, y, 0F)
    fun setPosition(x: Float, y: Float) {
        position = Vector3(x, y, 0F);
    }
    private var yMultiplier: Float = 1F;
    var velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, sprite.width, sprite.height)
    var sprite: Sprite = Sprite(Texture("attackhelicopter.PNG"))
    val texture: Texture
        get() = sprite.texture
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun setVelocity(multipliers: Array<Float>){
        velocity.x = SPEED * multipliers[0]
        velocity.y = SPEED * multipliers[1]
        if (multipliers[0] <= 0) {
            sprite.setFlip(false, false)
        }
        else{
            sprite.setFlip(true, false)
        }
    }
    // wallCollisions [top,bottom]
    // multipliers[x,y]
    fun changeVelocity(wallCollisions: Array<Boolean>, paddleCollision: Boolean, multipliers: Array<Float>){
        if (wallCollisions[0]) {
            velocity.y = -SPEED * abs(yMultiplier)
        }
        if (wallCollisions[1]) {
            velocity.y = SPEED * abs(yMultiplier)
        }
        if(paddleCollision){
            if (multipliers[0]<=0){
                sprite.setFlip(false, false)
            }
            else{
                sprite.setFlip(true, false)
            }
            velocity.x = multipliers[0] * SPEED
            velocity.y = multipliers[1] * SPEED
            yMultiplier = multipliers[1]
        }
    }
}