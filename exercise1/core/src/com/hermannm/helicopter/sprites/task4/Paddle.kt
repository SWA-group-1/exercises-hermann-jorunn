package com.hermannm.helicopter.sprites.task4

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Paddle(x: Float, y: Float) {
    companion object {
        const val SPEED = 100F
    }
    private var position: Vector3 = Vector3(x, y, 0F)
    private var velocity: Vector3 = Vector3(0F, 0F, 0F)
    private var paddle: Texture = Texture("paddle.png")

    //up down
    fun changeDirection(directions: Array<Boolean>) {
        if (directions[0]) {
            velocity.y = SPEED
        } else if (directions[1]) {
            velocity.y = -SPEED
        } else {
            velocity.y = 0F
        }
    }
    fun handleCollision(collisions: Array<Boolean>){
        if(collisions[0] && velocity.y>0){
            velocity.y = 0F
        }
        else if(collisions[1] && velocity.y<0){
            velocity.y = 0F
        }
    }

    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(0F, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }

    fun setPosition(x:Float, y:Float){
        position.x = x;
        position.y = y;
    }

    fun getPosition(): Vector3 {
        return position
    }

    fun getVelocity(): Vector3{
        return velocity
    }

    fun getTexture(): Texture {
        return paddle
    }
    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, paddle.getWidth().toFloat(), paddle.getHeight().toFloat())
    }
    fun dispose(){
        paddle.dispose()
    }
}