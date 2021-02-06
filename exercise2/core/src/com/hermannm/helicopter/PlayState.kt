package com.hermannm.helicopter

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.states.BackButton

abstract class PlayState : State() {
    protected val backButton: BackButton = BackButton()
    override fun render(sprites: SpriteBatch) {
        backButton.draw()
    }
    override fun dispose() {
        backButton.dispose()
    }
}