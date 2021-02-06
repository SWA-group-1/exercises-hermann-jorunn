package com.hermannm.helicopter.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.hermannm.helicopter.HelicopterGame

class BackButton {
    private val camera: OrthographicCamera = OrthographicCamera()
    private val viewport: Viewport = StretchViewport(HelicopterGame.WIDTH, HelicopterGame.HEIGHT, camera)
    private val stage: Stage = Stage(viewport, HelicopterGame.batch)
    private val backButton = Image(Texture("backButton.png"))
    private val backButtonSize: Float = 100F
    init {
        val inputMultiplexer: InputMultiplexer = Gdx.input.getInputProcessor() as InputMultiplexer;
        if (!inputMultiplexer.getProcessors().contains(stage)) {
            inputMultiplexer.addProcessor(stage);
        }
        backButton.setSize(backButtonSize, backButtonSize)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                GameStateManager.INSTANCE.set(MenuState())
            }
        })
        stage.addActor(backButton)
        backButton.setPosition(0f, HelicopterGame.HEIGHT - backButtonSize)
    }
    fun draw() {
        stage.draw()
    }
    fun dispose() {
        stage.dispose()
    }
}