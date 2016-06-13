package io.twere.oddkotlin

/**
 * Created by adamluissean on 09.06.16.
 */

interface CanShoot {
    fun shoot(): String
}

final class LaserBeam : CanShoot {
    override fun shoot(): String {
        return "Ziiiip!"
    }
}

final class WeaponsComposite(var weapons: Array<CanShoot>) {
    fun shoot(): String {
        return weapons.map { it -> it.shoot() }.get(0)
    }
}

final class RocketLauncher : CanShoot {
    override fun shoot(): String {
        return "Whoosh!"
    }
}

fun main(args: Array<String>) {
    val laser = LaserBeam()
    var weapons = WeaponsComposite(weapons = arrayOf(laser))
    weapons.shoot()

    val rocket = RocketLauncher()

    weapons = WeaponsComposite(weapons = arrayOf(laser, rocket))
    weapons.shoot()
}

