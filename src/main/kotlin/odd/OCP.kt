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

class CesiumBeam : CanShoot {
    override fun shoot(): String {
        return "Ciiiip!"
    }
}

class RocketLauncher : CanShoot {
    override fun shoot(): String {
        return "Whoosh!"
    }
}

class WeaponsComposite(var weapons: Array<CanShoot>) {
    fun shoot(): String {
        return weapons.map { it.shoot() }[0]
    }
}

fun main() {
    val laser = LaserBeam()
    var weapons = WeaponsComposite(weapons = arrayOf(laser))
    println(weapons.shoot())

    val rocket = RocketLauncher()
    val cBeam = CesiumBeam()

    weapons = WeaponsComposite(weapons = arrayOf(cBeam, rocket, laser))
    println(weapons.shoot())
}

