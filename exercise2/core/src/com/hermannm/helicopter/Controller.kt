package com.hermannm.helicopter

import com.hermannm.helicopter.model.Model

class Controller(
    private val model: Model
) {
    fun update(deltaTime: Float) {
        model.update(deltaTime)
    }
    fun up() {
        model.controlHelicopter(0, 1)
    }
    fun right() {
        model.controlHelicopter(1, 0)
    }
    fun down() {
        model.controlHelicopter(0, -1)
    }
    fun left() {
        model.controlHelicopter(-1, 0)
    }
    fun stop() {
        model.controlHelicopter(0, 0)
    }
}