The Principles of OOD in Kotlin 1.0.2
=====================================

S.O.L.I.D.
==========

* [The Single Responsibility Principle](#-the-single-responsibility-principle)
* [The Open Closed Principle](#-the-open-closed-principle)
* [The Liskov Substitution Principle](#-the-liskov-substitution-principle)
* [The Interface Segregation Principle](#-the-interface-segregation-principle)
* [The Dependency Inversion Principle](#-the-dependency-inversion-principle)


```kotlin
```

# 🔐 The Single Responsibility Principle

A class should have one, and only one, reason to change. ([read more](https://docs.google.com/open?id=0ByOwmqah_nuGNHEtcU5OekdDMkk))

Example:

```kotlin

interface CanBeOpened {
    fun open()
}

interface CanBeClosed {
    fun closed()
}

// I'm the door. I have an encapsulated state and you can change it using methods.
final class PodBayDoor : CanBeOpened, CanBeClosed {

    private enum class State {
        Open, Closed
    }

    private var state: State = State.Closed

    override fun open() {
        state = State.Open
    }

    override fun closed() {
        state = State.Closed
    }
}

// I'm only responsible for opening, no idea what's inside or how to close.
class DoorOpener(door: CanBeOpened) {

    val door: CanBeOpened = door

    fun execute() {
        door.open()
    }
}

// I'm only responsible for closing, no idea what's inside or how to open.
class DoorCloser(door: CanBeClosed) {
    val door: CanBeClosed = door

    fun execute() {
        door.closed()
    }
}

val door = PodBayDoor()

```
 
> ⚠ Only the `DoorOpener` is responsible for opening the door.

```kotlin

val doorOpener = DoorOpener(door)
doorOpener.execute()

```
 
> ⚠ If another operation should be made upon closing the door,
> like switching on the alarm, you don't have to change the `DoorOpener` class.

```kotlin

val doorCloser = DoorCloser(door)
doorCloser.execute()

```

# ✋ The Open Closed Principle

You should be able to extend a classes behavior, without modifying it. ([read more](http://docs.google.com/a/cleancoder.com/viewer?a=v&pid=explorer&chrome=true&srcid=0BwhCYaYDn8EgN2M5MTkwM2EtNWFkZC00ZTI3LWFjZTUtNTFhZGZiYmUzODc1&hl=en))

Example:
 
```kotlin

interface CanShoot {
    fun shoot(): String
}

// I'm a laser beam. I can shoot.
final class LaserBeam : CanShoot {
    override fun shoot(): String {
        return "Ziiiip!"
    }
}

// I have weapons and trust me I can fire them all at once. Boom! Boom! Boom!
final class WeaponsComposite(var weapons: Array<CanShoot>) {
    fun shoot(): String {
        return weapons.map { it -> it.shoot() }.get(0)
    }
}

val laser = LaserBeam()
var weapons = WeaponsComposite(weapons = arrayOf(laser))

weapons.shoot()

```
 
I'm a rocket launcher. I can shoot a rocket.
> ⚠️ To add rocket launcher support I don't need to change anything in existing classes.

```kotlin

final class RocketLauncher : CanShoot {
    override fun shoot(): String {
        return "Whoosh!"
    }
}


val rocket = RocketLauncher()

weapons = WeaponsComposite(weapons = arrayOf(laser, rocket))
weapons.shoot()

```

# 👥 The Liskov Substitution Principle

Derived classes must be substitutable for their base classes. ([read more](http://docs.google.com/a/cleancoder.com/viewer?a=v&pid=explorer&chrome=true&srcid=0BwhCYaYDn8EgNzAzZjA5ZmItNjU3NS00MzQ5LTkwYjMtMDJhNDU5ZTM0MTlh&hl=en))

Example:

```kotlin

```


# 🍴 The Interface Segregation Principle

Make fine grained interfaces that are client specific. ([read more](http://docs.google.com/a/cleancoder.com/viewer?a=v&pid=explorer&chrome=true&srcid=0BwhCYaYDn8EgOTViYjJhYzMtMzYxMC00MzFjLWJjMzYtOGJiMDc5N2JkYmJi&hl=en))

Example:
 
```kotlin

// I have a landing site.
interface LandingSiteHaving {
    var landingSite: String
}

// I can land on LandingSiteHaving objects.
interface Landing {
    fun landOn(on: LandingSiteHaving): String
}

// I have payload.
interface PayloadHaving {
    var payload: String
}

// I can fetch payload from vehicle (ex. via Canadarm).
final class InternationalSpaceStation {

```
 
> ⚠ Space station has no idea about landing capabilities of SpaceXCRS8.

```kotlin

    fun fetchPayload(vehicle: PayloadHaving): String {
        return "Deployer ${vehicle.payload} at April 10, 2016, 11:23 UTC"
    }
}

// I'm a barge - I have landing site (well, you get the idea).
final class OfCourseIStillLoveYouBarge : LandingSiteHaving {
    override var landingSite = "a barge on the Atlantic Ocean"
}

// I have payload and can land on things having landing site.
// I'm a very limited Space Vehicle, I know.
final class SpaceXCRS8 : Landing, PayloadHaving {

    override var payload = "BEAM and some Cube Sats"

```
 
> ⚠ CRS8 knows only about the landing site information.

```kotlin

    override fun landOn(on: LandingSiteHaving): String {
            return "Landed on ${on.landingSite} at April 8, 2016 20:52 UTC"
    }
}

val crs8 = SpaceXCRS8()
val barge = OfCourseIStillLoveYouBarge()
val spaceStation = InternationalSpaceStation()

spaceStation.fetchPayload(crs8)
crs8.landOn(barge)

```

# 🔩 The Dependency Inversion Principle

Depend on abstractions, not on concretions. ([read more](http://docs.google.com/a/cleancoder.com/viewer?a=v&pid=explorer&chrome=true&srcid=0BwhCYaYDn8EgMjdlMWIzNGUtZTQ0NC00ZjQ5LTkwYzQtZjRhMDRlNTQ3ZGMz&hl=en))

Example:

```kotlin

interface TimeTraveling {
    fun travelInTime(time: String): String
}

final class DeLorean : TimeTraveling {
    override fun travelInTime(time: String): String {
        return "Used Flux Capacitor and travelled in time by: ${time}s"
    }
}

final class EmmettBrown(private val timeMachine: TimeTraveling) {

```
 
> ⚠ Emmet Brown is given the `DeLorean` as a `TimeTraveling` device, not the concrete class `DeLorean`.

```kotlin

    fun travelInTime(time: String): String {
        return timeMachine.travelInTime(time)
    }
}

val timeMachine = DeLorean()

val mastermind = EmmettBrown(timeMachine)
mastermind.travelInTime("3445433")

```

Info
====

📖 Descriptions from: [ochococo/OOD-Principles-In-Swift](https://github.com/ochococo/OOD-Principles-In-Swift) Thank's
