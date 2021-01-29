package com.hermannm.helicopter

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table

class Controller {
    val camera: OrthographicCamera = OrthographicCamera()
    val viewport: Viewport = FitViewport(HelicopterGame.WIDTH, HelicopterGame.HEIGHT, camera)
    val stage: Stage = Stage(viewport, HelicopterGame.batch)
    val table: Table = Table()
    var upPressed: Boolean = false
    var downPressed: Boolean = false
    var rightPressed: Boolean = false
    var leftPressed: Boolean = false
    val upImg = Image(Texture("upTexture.png"))
    val downImg = Image(Texture("downTexture.png"))
    val rightImg = Image(Texture("rightTexture.png"))
    val leftImg = Image(Texture("leftTexture.png"))
    init {
        Gdx.input.setInputProcessor(stage)
        table.left().bottom()
        upImg.setSize(50F, 50F)
        upImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                upPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                upPressed = true
                return true
            }
        })
        downImg.setSize(50F, 50F)
        downImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                downPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                downPressed = true
                return true
            }
        })
        rightImg.setSize(50F, 50F)
        rightImg.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                rightPressed = false
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                rightPressed = true
                return true
            }
        })
        leftImg.setSize(50F, 50F)
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
        table.row().pad(5F,5F,5F,5F)
        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight())
        table.add()
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight())
        table.row().padBottom(5F)
        table.add()
        table.add(downImg).size(downImg.getWidth(), downImg.getHeight())
        table.add()
        stage.addActor(table)
    }
    fun draw() {
        stage.draw()
    }
    fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }
}