package com.hermannm.helicopter.view

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.model.Bounds

class BackgroundView {
    private val background: Texture = Texture("background.jpg")
    fun draw(sprites: SpriteBatch) {
        sprites.draw(background, 0F, 0F, Bounds.INSTANCE.right, Bounds.INSTANCE.top)
    }
}