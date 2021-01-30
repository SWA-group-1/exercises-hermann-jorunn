package com.hermannm.helicopter.sprites.task3

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Animation(private val frames: Array<Texture>, cycleTime: Float) {
    private var frame: Int = 0
    private val maxFrameTime: Float = cycleTime / frames.size
    private var currentFrameTime: Float = 0F
    fun update(deltaTime: Float) {
        currentFrameTime += deltaTime
        if (currentFrameTime > maxFrameTime) {
            frame++
            currentFrameTime = 0F
        }
        if (frame >= frames.size) {
            frame = 0
        }
    }
    fun getFrame(): Texture {
        return frames[frame]
    }
}