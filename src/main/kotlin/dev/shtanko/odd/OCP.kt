/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.odd

/**
 * The Open Closed Principle
 */
interface CanShoot {
    fun shoot(): String
}

data object LaserBeam : CanShoot {
    override fun shoot(): String = "Ziiiip!"
}

data object CesiumBeam : CanShoot {
    override fun shoot(): String = "Ciiiip!"
}

data object RocketLauncher : CanShoot {
    override fun shoot(): String = "Whoosh!"
}

/**
 * Represents a composite of weapons, allowing for the aggregation of different
 * `CanShoot` implementations.
 * This class demonstrates the Composite design pattern, enabling clients to
 * treat individual objects and compositions uniformly.
 *
 * @property weapons An array of `CanShoot` objects representing the collection
 * of weapons.
 */
class WeaponsComposite(var weapons: Array<CanShoot>) {
    fun shoot(): String = weapons.map { it.shoot() }.first()
}

fun main() {
    val laser = LaserBeam
    var weapons = WeaponsComposite(weapons = arrayOf(laser))
    println(weapons.shoot())

    val rocket = RocketLauncher
    val cesiumBeam = CesiumBeam

    weapons = WeaponsComposite(weapons = arrayOf(cesiumBeam, rocket, laser))
    println(weapons.shoot())
}
