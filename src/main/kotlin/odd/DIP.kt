package odd

/**
 * The Dependency Inversion Principle
 * Created by adamluissean on 13.06.16.
 */

interface CoffeeMachine {
    fun brew(coffee:Coffee)
}

interface Coffee

class Arabica : Coffee


class Rabusta : Coffee

class BrewMachine : CoffeeMachine {
    override fun brew(coffee: Coffee) {
        println("Brew: $coffee")
    }
}

fun main() {

    val brewMachine = BrewMachine()
    brewMachine.brew(Arabica())
    brewMachine.brew(Rabusta())

}