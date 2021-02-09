package com.hermannm.helicopter.view

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.model.Helicopter

class HelicopterView(
    private val helicopter: Helicopter
) {
    private val sprite: Sprite = Sprite(Texture("attackhelicopter.PNG"))
    fun draw(sprites: SpriteBatch) {
        if (helicopter.velocity.x > 0) {
            sprite.setFlip(true, false)
        } else if (helicopter.velocity.x < 0) {
            sprite.setFlip(false, false)
        }
        sprites.draw(sprite, helicopter.position.x, helicopter.position.y)
    }
}