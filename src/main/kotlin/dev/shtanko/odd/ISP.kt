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

interface LandingSiteHaving {
    var landingSite: String
}

interface Landing {
    fun landOn(on: LandingSiteHaving): String
}

interface PayloadHaving {
    var payload: String
}

class InternationalSpaceStation {
    fun fetchPayload(vehicle: PayloadHaving): String = "Deployed ${vehicle.payload} at April 10, 2016, 11:23 UTC"
}

class OfCourseIStillLoveYouBarge : LandingSiteHaving {
    override var landingSite = "a barge on the Atlantic Ocean"
}

class SpaceXCRS8 : Landing, PayloadHaving {
    override var payload = "BEAM and some Cube Sats"

    override fun landOn(on: LandingSiteHaving): String = "Landed on ${on.landingSite} at April 8, 2016 20:52 UTC"
}

fun main() {
    val crs8 = SpaceXCRS8()
    val barge = OfCourseIStillLoveYouBarge()
    val spaceStation = InternationalSpaceStation()

    spaceStation.fetchPayload(crs8)
    crs8.landOn(barge)
}
