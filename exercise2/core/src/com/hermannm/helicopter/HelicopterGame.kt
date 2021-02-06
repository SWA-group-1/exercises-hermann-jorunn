package com.hermannm.helicopter

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.states.GameStateManager
import com.hermannm.helicopter.states.MenuState

class HelicopterGame : ApplicationAdapter() {
    companion object {
        const val WIDTH = 800F
        const val HEIGHT = 480F
        const val TITLE = "Helicopter"
        var batch: SpriteBatch? = null
    }
    override fun create() {
        batch = SpriteBatch()
        Gdx.input.setInputProcessor(InputMultiplexer())
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        GameStateManager.INSTANCE.push(MenuState())
    }
    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        GameStateManager.INSTANCE.update(Gdx.graphics.getDeltaTime())
        GameStateManager.INSTANCE.render(batch!!)
    }

    override fun dispose() {
        batch?.dispose()
    }
}