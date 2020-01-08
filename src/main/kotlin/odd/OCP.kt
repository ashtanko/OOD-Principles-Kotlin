package odd

/**
 * The Open Closed Principle
 * Created by adamluissean on 09.06.16.
 */

interface CanShoot {
    fun shoot(): String
}

class LaserBeam : CanShoot {
    override fun shoot(): String {
        return "Ziiiip!"
    }
}

class WeaponsComposite(var weapons: Array<CanShoot>) {
    fun shoot(): String {
        return weapons.map { it -> it.shoot() }.get(0)
    }
}

class RocketLauncher : CanShoot {
    override fun shoot(): String {
        return "Whoosh!"
    }
}

fun main() {
    val laser = LaserBeam()
    var weapons = WeaponsComposite(weapons = arrayOf(laser))
    weapons.shoot()

    val rocket = RocketLauncher()

    weapons = WeaponsComposite(weapons = arrayOf(laser, rocket))
    weapons.shoot()
}

