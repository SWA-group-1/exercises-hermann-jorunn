package com.hermannm.helicopter

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.physics.box2d.*
import com.hermannm.helicopter.sprites.task3.Helicopter3

class WorldContactListener : ContactListener {
    override fun beginContact(contact: Contact) {
        val fixtureA: Fixture = contact.getFixtureA()
        val fixtureB: Fixture = contact.getFixtureB()
        val userDataA = fixtureA.getUserData()
        val userDataB = fixtureB.getUserData()
        if (userDataA is Pair<*, *> && userDataB is Pair<*, *>) {
            if (
                userDataA.first is String
                && userDataA.second is Helicopter3
                && userDataB.first is String
                && userDataB.second is Helicopter3
            ) {
                if (
                        (userDataA.first == "top" && userDataB.first == "bottom")
                        || (userDataA.first == "bottom" && userDataB.first == "top")
                        || (userDataA.first == "right" && userDataB.first == "left")
                        || (userDataA.first == "left" && userDataB.first == "right")
                ) {
                    (userDataA.second as Helicopter3).helicopterCollision(userDataA.first as String)
                    (userDataB.second as Helicopter3).helicopterCollision(userDataB.first as String)
                }
            }
        }
    }
    override fun endContact(contact: Contact) {
    }
    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
    }
    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
    }
}