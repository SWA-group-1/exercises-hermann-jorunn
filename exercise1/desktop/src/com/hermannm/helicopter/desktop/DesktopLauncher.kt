package com.hermannm.helicopter.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.hermannm.helicopter.Helicopter

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = Helicopter.WIDTH.toInt()
        config.height = Helicopter.HEIGHT.toInt()
        config.title = Helicopter.TITLE
        LwjglApplication(Helicopter(), config)
    }
}