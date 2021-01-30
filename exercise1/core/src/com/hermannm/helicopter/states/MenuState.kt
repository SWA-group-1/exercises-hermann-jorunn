package com.hermannm.helicopter.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
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
    private val viewport: Viewport = StretchViewport(HelicopterGame.WIDTH, HelicopterGame.HEIGHT, camera)
    private val stage: Stage = Stage(viewport, HelicopterGame.batch)
    private val table: Table = Table()
    // TODO: Replace task images
    private val task1Img = Image(Texture("task1.png"))
    private val task2Img = Image(Texture("task2.png"))
    private val task3Img = Image(Texture("task3.png"))
    private val task4Img = Image(Texture("task4.png"))
    init {
        Gdx.input.setInputProcessor(stage)
        table.left().bottom()
        task1Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.set(PlayState1(stateManager))
            }
        })
        task2Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.set(PlayState2(stateManager))
            }
        })
        task3Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                stateManager.set(PlayState3(stateManager))
            }
        })
        task4Img.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                // TODO: Change PlayState3 to PlayState4
                stateManager.set(PlayState3(stateManager))
            }
        })
        table.add(task1Img).size((HelicopterGame.WIDTH / 2), HelicopterGame.HEIGHT / 2)
        table.add(task2Img).size((HelicopterGame.WIDTH / 2), HelicopterGame.HEIGHT / 2)
        table.row()
        table.add(task3Img).size((HelicopterGame.WIDTH / 2), HelicopterGame.HEIGHT / 2)
        table.add(task4Img).size((HelicopterGame.WIDTH / 2), HelicopterGame.HEIGHT / 2)
        stage.addActor(table)
    }
    override fun handleInput() {}
    override fun update(deltaTime: Float) {
        handleInput()
    }
    override fun render(sprites: SpriteBatch) {
        sprites.setProjectionMatrix(camera.combined)
        stage.draw()
    }
    override fun dispose(){
        stage.dispose()
    }
}