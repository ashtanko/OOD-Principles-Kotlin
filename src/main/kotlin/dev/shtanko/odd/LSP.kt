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
 * The Liskov Substitution Principle.
 */
private interface Bird {
    fun eat() {
        println("Eat!")
    }
}

private interface FlightBird : Bird {
    fun fly() {
        println("Fly!")
    }
}

private interface NonFlightBird : Bird {
    // TODO
}

private data object Crow : FlightBird {
    override fun fly() {
        super.fly()
        println("It is Crow - it can fly")
    }

    override fun eat() {
        super.eat()
    }
}

private data object Ostrich : NonFlightBird {
    override fun eat() {
        super.eat()
        println("It is Ostrich - it can eat but it can't fly")
    }
}

fun main() {
    val crow: FlightBird = Crow
    crow.fly()

    val ostrich: NonFlightBird = Ostrich
    ostrich.eat()
}
