package com.hermannm.helicopter.view

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
import com.hermannm.helicopter.Controller
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.model.Bounds

class Navigation(
    private val controller: Controller,
    camera: OrthographicCamera
) {
    private val upImg = Image(Texture("upTexture.png"))
    private val downImg = Image(Texture("downTexture.png"))
    private val rightImg = Image(Texture("rightTexture.png"))
    private val leftImg = Image(Texture("leftTexture.png"))
    private val buttonSize = 50f
    private val buttonPadding = 5f
    private val viewport: Viewport = StretchViewport(Bounds.INSTANCE.right, Bounds.INSTANCE.top, camera)
    private val stage: Stage = Stage(viewport, HelicopterGame.batch)
    private val table: Table = Table()
    init {
        val inputMultiplexer: InputMultiplexer = Gdx.input.inputProcessor as InputMultiplexer
        if (!inputMultiplexer.processors.contains(stage)) {
            inputMultiplexer.addProcessor(stage);
        }
        table.left().bottom()
        upImg.setSize(buttonSize, buttonSize)
        upImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                controller.stop()
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                controller.up()
                return true
            }
        })
        downImg.setSize(buttonSize, buttonSize)
        downImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                controller.stop()
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                controller.down()
                return true
            }
        })
        rightImg.setSize(buttonSize, buttonSize)
        rightImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                controller.stop()
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                controller.right()
                return true
            }
        })
        leftImg.setSize(buttonSize, buttonSize)
        leftImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                controller.stop()
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                controller.left()
                return true
            }
        })
        table.add()
        table.add(upImg).size(upImg.width, upImg.height)
        table.add()
        table.row()
        table.add(leftImg).size(leftImg.width, leftImg.height)
        table.add()
        table.add(rightImg).size(rightImg.width, rightImg.height)
        table.row()
        table.add()
        table.add(downImg).size(downImg.width, downImg.height)
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