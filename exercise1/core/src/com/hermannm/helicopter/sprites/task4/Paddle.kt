package com.hermannm.helicopter.sprites.task4

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Paddle(x: Float, y: Float) {
    companion object {
        const val SPEED = 100F
    }
    var position: Vector3 = Vector3(x, y, 0F)
    fun setPosition(x:Float, y:Float) {
        position.x = x
        position.y = y
    }
    var velocity: Vector3 = Vector3(0F, 0F, 0F)
    var texture: Texture = Texture("paddle.png")
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, texture.width.toFloat(), texture.height.toFloat())
    // directions: [up, down]
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(0F, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun changeDirection(directions: Array<Boolean>) {
        velocity.y = when {
            directions[0] -> SPEED
            directions[1] -> -SPEED
            else -> 0F
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
    fun dispose(){
        texture.dispose()
    }
}