package com.hermannm.helicopter

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.StretchViewport

class Controller {
    var upPressed: Boolean = false
    var downPressed: Boolean = false
    var rightPressed: Boolean = false
    var leftPressed: Boolean = false
    private val upImg = Image(Texture("upTexture.png"))
    private val downImg = Image(Texture("downTexture.png"))
    private val rightImg = Image(Texture("rightTexture.png"))
    private val leftImg = Image(Texture("leftTexture.png"))
    private val buttonSize = 50f
    private val buttonPadding = 5f
    private val camera: OrthographicCamera = OrthographicCamera()
    private val viewport: Viewport = StretchViewport(HelicopterGame.WIDTH, HelicopterGame.HEIGHT, camera)
    private val stage: Stage = Stage(viewport, HelicopterGame.batch)
    private val table: Table = Table()
    init {
        val inputMultiplexer: InputMultiplexer = Gdx.input.getInputProcessor() as InputMultiplexer;
        if (!inputMultiplexer.getProcessors().contains(stage)) {
            inputMultiplexer.addProcessor(stage);
        }
        table.left().bottom()
        upImg.setSize(buttonSize, buttonSize)
        upImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                upPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                upPressed = true
                return true
            }
        })
        downImg.setSize(buttonSize, buttonSize)
        downImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                downPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                downPressed = true
                return true
            }
        })
        rightImg.setSize(buttonSize, buttonSize)
        rightImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                rightPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                rightPressed = true
                return true
            }
        })
        leftImg.setSize(buttonSize, buttonSize)
        leftImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                leftPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                leftPressed = true
                return true
            }
        })
        table.add()
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight())
        table.add()
        table.row()
        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight())
        table.add()
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight())
        table.row()
        table.add()
        table.add(downImg).size(downImg.getWidth(), downImg.getHeight())
        table.add()
        stage.addActor(table)
        table.setPosition(buttonPadding, buttonPadding)
    }
    fun draw() {
        stage.draw()
    }
    fun dispose() {
        stage.dispose()
    }
}