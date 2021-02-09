package com.hermannm.helicopter.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.hermannm.helicopter.HelicopterGame
import com.hermannm.helicopter.model.Bounds

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = Bounds.INSTANCE.right.toInt()
        config.height = Bounds.INSTANCE.top.toInt()
        config.title = HelicopterGame.TITLE
        LwjglApplication(HelicopterGame(), config)
    }
}