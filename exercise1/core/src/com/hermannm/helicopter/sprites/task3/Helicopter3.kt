package com.hermannm.helicopter.sprites.task3

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.*

class Helicopter3(x: Float, y: Float, val world: World) : Sprite() {
    companion object {
        const val SPEED = 100F
    }
    private val position: Vector3 = Vector3(x, y, 0F)
    private val velocity: Vector3 = Vector3(-SPEED, SPEED, 0F)
    private val helicopterTextures: Array<Texture> = arrayOf(
        Texture("heli1.png"),
        Texture("heli2.png"),
        Texture("heli3.png"),
        Texture("heli4.png")
    )
    val helicopterWidth get() = helicopterTextures[0].getWidth()
    val helicopterHeight get() = helicopterTextures[1].getHeight()
    private val helicopterAnimation: Animation = Animation(
        helicopterTextures,
        0.5F
    )
    // edges: [top, right, bottom, left]
    val edges: HashMap<String, EdgeShape> = hashMapOf(
            "top" to EdgeShape(),
            "right" to EdgeShape(),
            "bottom" to EdgeShape(),
            "left" to EdgeShape()
    )
    val bodyDef: BodyDef = BodyDef()
    init {
        bodyDef.position.set(position.x, position.y)
        bodyDef. type = BodyDef.BodyType.DynamicBody
    }
    var body: Body = world.createBody(bodyDef)
    init {
        edges["top"]!!.set(
                (-helicopterWidth/2).toFloat(),
                (helicopterHeight/2).toFloat(),
                (helicopterWidth/2).toFloat(),
                (helicopterHeight/2).toFloat()
        )
        edges["right"]!!.set(
                (helicopterWidth/2).toFloat(),
                (helicopterHeight/2).toFloat(),
                (helicopterWidth/2).toFloat(),
                (-helicopterHeight/2).toFloat()
        )
        edges["bottom"]!!.set(
                (-helicopterWidth/2).toFloat(),
                (-helicopterHeight/2).toFloat(),
                (helicopterWidth/2).toFloat(),
                (-helicopterHeight/2).toFloat()
        )
        edges["left"]!!.set(
                (-helicopterWidth/2).toFloat(),
                (-helicopterHeight/2).toFloat(),
                (-helicopterWidth/2).toFloat(),
                (helicopterHeight/2).toFloat()
        )
        for ((edge, shape) in edges) {
            val fixtureDef: FixtureDef = FixtureDef()
            fixtureDef.shape = shape
            fixtureDef.isSensor = true
            body.createFixture(fixtureDef).setUserData(edge to this)
        }
    }
    fun helicopterCollision(edge: String) {
        if (edge == "top") {
            changeDirection(arrayOf(true, false, false, false))
        }else if (edge == "right") {
            changeDirection(arrayOf(false, true, false, false))
        }else if (edge == "bottom") {
            changeDirection(arrayOf(false, false, true, false))
        }else if (edge == "left") {
            changeDirection(arrayOf(false, false, false, true))
        }
    }
    fun update(deltaTime: Float) {
        helicopterAnimation.update(deltaTime)
        velocity.scl(deltaTime)
        position.add(velocity.x, velocity.y, 0F)
        velocity.scl(1/deltaTime)
        body.setTransform(position.x, position.y, 0F)
    }
    fun changeDirection(collisions: Array<Boolean>) {
        if (collisions[0]) {
            velocity.y = -SPEED
        }
        if (collisions[1]) {
            velocity.x = -SPEED
            this.setFlip(false, false)
        }
        if (collisions[2]) {
            velocity.y = SPEED
        }
        if (collisions[3]) {
            velocity.x = SPEED
            this.setFlip(true, false)
        }
    }
    fun getPosition(): Vector3 {
        return position
    }
    override fun getTexture(): Texture {
        return helicopterAnimation.getFrame()
    }
    fun getBounds(): Rectangle {
        return Rectangle(position.x, position.y, helicopterTextures[0].getWidth().toFloat(), helicopterTextures[0].getHeight().toFloat())
    }
}