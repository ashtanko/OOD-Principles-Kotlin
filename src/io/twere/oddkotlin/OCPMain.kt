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

}


fun main(args: Array<String>) {

}

