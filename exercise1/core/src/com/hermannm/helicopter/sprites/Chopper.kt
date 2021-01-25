package com.hermannm.helicopter.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Chopper(x: Float, y: Float) {
    private var position: Vector3 = Vector3(x, y, 0F)
    private var velocity: Vector3 = Vector3(15F, 15F, 0F)
    private var chopper: Texture = Texture("attackhelicopter.png")
    fun update(deltaTime: Float) {
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
    }
    fun getPosition(): Vector3 {
        return position;
    }
    fun getTexture(): Texture {
        return chopper;
    }
}