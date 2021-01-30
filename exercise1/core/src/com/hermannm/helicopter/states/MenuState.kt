package com.hermannm.helicopter.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.states.task1.PlayState1
import com.hermannm.helicopter.states.task2.PlayState2
import com.hermannm.helicopter.states.task3.PlayState3

class MenuState(stateManager: GameStateManager): State(stateManager) {
    init {
        camera.setToOrtho(false, HelicopterGame.WIDTH, HelicopterGame.HEIGHT)
    }
    private var background: Texture = Texture("background.jpg")
    private var playButton: Texture = Texture("playbutton.png")
    val viewport: Viewport = StretchViewport(HelicopterGame.WIDTH, HelicopterGame.HEIGHT, camera)
    val stage: Stage = Stage(viewport, HelicopterGame.batch)
    val table: Table = Table()
    // TODO: Replace task images
    val task1Img = Image(Texture("task1.png"))
    val task2Img = Image(Texture("task2.png"))
    val task3Img = Image(Texture("task3.png"))
    val task4Img = Image(Texture("task4.png"))
    init {
        Gdx.input.setInputProcessor(stage)
        table.left().bottom()
        // task1Img.setSize((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        task1Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.set(PlayState1(stateManager))
            }
        })
        // task2Img.setSize((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        task2Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.set(PlayState2(stateManager))
            }
        })
        // task3Img.setSize((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        task3Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.set(PlayState3(stateManager))
            }
        })
        // task4Img.setSize((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        task4Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                // TODO: Change PlayState3 to PlayState4
                stateManager.set(PlayState3(stateManager))
            }
        })
        table.add(task1Img).size((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        table.add(task2Img).size((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        table.row()
        table.add(task3Img).size((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        table.add(task4Img).size((viewport.screenWidth / 2).toFloat(), (viewport.screenHeight / 2).toFloat())
        stage.addActor(table)
    }
    override fun handleInput() {
    }
    override fun update(deltaTime: Float) {
        handleInput()
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        sprites.begin()
        sprites.draw(background, 0F, 0F)
        sprites.end()
        stage.draw()
    }
    override fun dispose() {
        background.dispose()
        playButton.dispose()
    }
}