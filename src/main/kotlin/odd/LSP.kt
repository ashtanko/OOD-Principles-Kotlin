package odd

/**
 * The Liskov Substitution Principle.
 * Created by adamluissean on 13.06.16.
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

}

private class Crow : FlightBird {

    override fun fly() {
        super.fly()

        println("It is Crow - it can fly")
    }

    override fun eat() {
        super.eat()
    }
}

private class Ostrich : NonFlightBird {
    override fun eat() {
        super.eat()
        println("It is Ostrich - it can eat but it can't fly")
    }
}

fun main() {
    val crow: FlightBird = Crow()
    crow.fly()

    val ostrich: NonFlightBird = Ostrich()
    ostrich.eat()
}
