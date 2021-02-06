package com.hermannm.helicopter.sprites.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Animation(
    private val frames: Array<TextureRegion>,
    private val timePerFrame: Float
) {
    private var frameIndex: Int = 0
    val frame: TextureRegion
        get() = frames[frameIndex]
    private var currentFrameTime: Float = 0F
    fun update(deltaTime: Float) {
        currentFrameTime += deltaTime
        if (currentFrameTime > timePerFrame) {
            frameIndex++
            currentFrameTime = 0F
        }
        if (frameIndex >= frames.size) {
            frameIndex = 0
        }
    }
}