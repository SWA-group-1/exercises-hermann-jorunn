package com.hermannm.helicopter.sprites.task4

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.hermannm.helicopter.sprites.task1.Helicopter1
import kotlin.math.abs

class Helicopter4(x: Float, y: Float) : Sprite() {
    companion object {
        const val SPEED = 300F
    }
    private var position: Vector3 = Vector3(x, y, 0F)
    private var yMultiplier: Float = 1F;
    private var velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    private var helicopter: Sprite = Sprite(Texture("attackhelicopter.PNG"))
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun setVelocity(multipliers: Array<Float>){
        velocity.x = SPEED*multipliers[0]
        velocity.y = SPEED*multipliers[1]
        if (multipliers[0]<=0){
            helicopter.setFlip(false, false)
        }
        else{
            helicopter.setFlip(true, false)
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
            if (multipliers[0]<=0){
                helicopter.setFlip(false, false)
            }
            else{
                helicopter.setFlip(true, false)
            }
            velocity.x = multipliers[0] * SPEED
            velocity.y = multipliers[1]* SPEED
            yMultiplier = multipliers[1]
        }
    }

    override fun setPosition(x: Float, y: Float) {
        position = Vector3(x, y, 0F);
    }

    fun getPosition(): Vector3 {
        return position
    }
    override fun getTexture(): Texture {
        return helicopter.texture
    }
    fun getVelocity(): Vector3{
        return velocity
    }

    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, helicopter.getWidth().toFloat(), helicopter.getHeight().toFloat())
    }
    fun getSprite(): Sprite{
        return helicopter
    }

    fun dispose(){

    }
}