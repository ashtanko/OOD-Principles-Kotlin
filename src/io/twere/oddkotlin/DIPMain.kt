package io.twere.oddkotlin

/**
 * Created by adamluissean on 13.06.16.
 */

interface TimeTraveling {
    fun travelInTime(time: String): String
}

final class DeLorean : TimeTraveling {
    override fun travelInTime(time: String): String {
        return "Used Flux Capacitor and travelled in time by: ${time}s"
    }
}

final class EmmettBrown(private val timeMachine: TimeTraveling) {
    fun travelInTime(time: String): String {
        return timeMachine.travelInTime(time)
    }
}

fun main(args: Array<String>) {

    val timeMachine = DeLorean()

    val mastermind = EmmettBrown(timeMachine)
    mastermind.travelInTime("3445433")
}