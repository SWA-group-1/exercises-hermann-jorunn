package com.hermannm.helicopter.sprites.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter3(x: Float, y: Float) {
    companion object {
        const val SPEED = 100F
    }
    private val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    private val helicopterTextures: Array<Texture> = arrayOf(
        Texture("heli1.png"),
        Texture("heli2.png"),
        Texture("heli3.png"),
        Texture("heli4.png")
    )
    private val helicopterAnimation: Animation = Animation(
        helicopterTextures,
        0.5F
    )
    var helicopterSprite = Sprite(helicopterAnimation.getFrame())
    fun update(deltaTime: Float) {
        helicopterAnimation.update(deltaTime)
        helicopterSprite = Sprite(helicopterAnimation.getFrame())
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
            helicopterSprite.setFlip(false, false)
        }
        if (collisions[2]) {
            velocity.y = SPEED
        }
        if (collisions[3]) {
            velocity.x = SPEED
            helicopterSprite.setFlip(true, false)
        }
    }
    fun getPosition(): Vector3 {
        return position
    }
    fun getTexture(): Texture {
        return helicopterAnimation.getFrame()
    }
    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, helicopterTextures[0].getWidth().toFloat(), helicopterTextures[0].getHeight().toFloat())
    }
}