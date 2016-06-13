package io.twere.oddkotlin

/**
 * Created by adamluissean on 13.06.16.
 */

interface LandingSiteHaving {
    var landingSite: String
}

interface Landing {
    fun landOn(on: LandingSiteHaving): String
}

interface PayloadHaving {
    var payload: String
}

final class InternationalSpaceStation {

    fun fetchPayload(vehicle: PayloadHaving): String {
        return "Deployer ${vehicle.payload} at April 10, 2016, 11:23 UTC"
    }
}

final class OfCourseIStillLoveYouBarge : LandingSiteHaving {
    override var landingSite = "a barge on the Atlantic Ocean"
}

final class SpaceXCRS8 : Landing, PayloadHaving {

    override var payload = "BEAM and some Cube Sats"

    override fun landOn(on: LandingSiteHaving): String {
        return "Landed on ${on.landingSite} at April 8, 2016 20:52 UTC"
    }
}

fun main(args: Array<String>) {
    val crs8 = SpaceXCRS8()
    val barge = OfCourseIStillLoveYouBarge()
    val spaceStation = InternationalSpaceStation()

    spaceStation.fetchPayload(crs8)
    crs8.landOn(barge)
}