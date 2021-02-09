package com.hermannm.helicopter

import com.hermannm.helicopter.model.Model
import com.hermannm.helicopter.view.View

class Controller(
    private val model: Model,
    private val view: View
) {
    fun update(deltaTime: Float) {
        handleInput()
        model.update(deltaTime)
    }
    fun handleInput() {
        when {
            view.navigation.upPressed -> {
                model.controlHelicopter(0, 1)
            }
            view.navigation.rightPressed -> {
                model.controlHelicopter(1, 0)
            }
            view.navigation.downPressed -> {
                model.controlHelicopter(0, -1)
            }
            view.navigation.leftPressed -> {
                model.controlHelicopter(-1, 0)
            }
            else -> {
                model.controlHelicopter(0, 0)
            }
        }
    }
}