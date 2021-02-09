package com.hermannm.helicopter

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.hermannm.helicopter.model.Model
import com.hermannm.helicopter.view.View

class HelicopterGame : ApplicationAdapter() {
    companion object {
        const val TITLE = "Helicopter"
        var batch: SpriteBatch? = null
    }
    private var model: Model? = null
    private var view: View? = null
    private var controller: Controller? = null
    override fun create() {
        Gdx.input.inputProcessor = InputMultiplexer()
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        batch = SpriteBatch()
        model = Model()
        view = View(model!!)
        controller = Controller(model!!, view!!)
    }
    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        controller?.update(Gdx.graphics.deltaTime)
        view?.render(batch!!)
    }
    override fun dispose() {
        batch?.dispose()
    }
}