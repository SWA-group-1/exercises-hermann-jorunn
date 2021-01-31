package com.hermannm.helicopter.sprites.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Helicopter3(x: Float, y: Float) {
    val position: Vector3 = Vector3(x, y, 0F)
    private val speed = ((50..200).random()).toFloat()
    private val velocity: Vector3 = Vector3(-speed, speed, 0F)
    val bounds: Rectangle
        get() = Rectangle(position.x, position.y, sprite.width, sprite.height)
    private val textures: Array<TextureRegion> = arrayOf(
        TextureRegion(Texture("heli1.png")),
        TextureRegion(Texture("heli2.png")),
        TextureRegion(Texture("heli3.png")),
        TextureRegion(Texture("heli4.png"))
    )
    private val animation: Animation = Animation(textures, 0.5F)
    val sprite: Sprite = Sprite(animation.frame)
    private var flipped: Boolean = false
    fun update(deltaTime: Float) {
        animation.update(deltaTime)
        sprite.setRegion(animation.frame)
        if (flipped && !sprite.isFlipX) {
            sprite.setFlip(true, false)
        }
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1 / deltaTime)
    }
    fun changeDirection(collisions: HashMap<String, Boolean>) {
        if (collisions["top"]!!) {
            velocity.y = -speed
        }
        if (collisions["right"]!!) {
            velocity.x = -speed
            flipped = false
        }
        if (collisions["bottom"]!!) {
            velocity.y = speed
        }
        if (collisions["left"]!!) {
            velocity.x = speed
            flipped = true
        }
    }
}