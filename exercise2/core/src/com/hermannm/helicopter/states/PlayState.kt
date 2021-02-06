package com.hermannm.helicopter.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class PlayState : State() {
    protected val backButton: BackButton = BackButton()
    override fun render(sprites: SpriteBatch) {
        backButton.draw()
    }
    override fun dispose() {
        backButton.dispose()
    }
}