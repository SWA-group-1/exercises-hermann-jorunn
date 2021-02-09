package com.hermannm.helicopter.model

class Model {
    val helicopter: Helicopter = Helicopter(150f, 100f)
    // xMultiplier: 1 / -1 / 0, yMultiplier: 1 / -1 / 0
    fun controlHelicopter(xMultiplier: Int, yMultiplier: Int) {
        var x = xMultiplier
        var y = yMultiplier
        if (
            yMultiplier == 1
            && helicopter.position.y + helicopter.height >= Bounds.INSTANCE.top
        ) {
            y = 0
        } else if (
            yMultiplier == -1
            && helicopter.position.y <= Bounds.INSTANCE.bottom
        ) {
            y = 0
        }
        if (
            xMultiplier == 1
            && helicopter.position.x + helicopter.width >= Bounds.INSTANCE.right
        ) {
            x = 0
        } else if (
            xMultiplier == -1
            && helicopter.position.x <= Bounds.INSTANCE.left
        ) {
            x = 0
        }
        helicopter.control(x, y)
    }
    fun update(deltaTime: Float) {
        helicopter.update(deltaTime)
    }
}