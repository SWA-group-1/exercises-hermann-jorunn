package com.hermannm.helicopter

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
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
    private var stateManager: GameStateManager? = null
    var controller: Controller? = null
    override fun create() {
        batch = SpriteBatch()
        stateManager = GameStateManager()
        controller = Controller()
        Gdx.gl.glClearColor(1F, 0F, 0F, 1F)
        stateManager?.push(MenuState(stateManager!!))
    }
    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stateManager?.update(Gdx.graphics.getDeltaTime())
        stateManager?.render(batch!!)
    }

    override fun dispose() {
        batch?.dispose()
    }
}