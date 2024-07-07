# The Principles of OOD in Kotlin

## Table of Contents

* [Creational Patterns](#creational)
	* [Singleton](#singleton)
	* [Prototype](#prototype)
	* [Object Pool](#objectpool)
	* [Builder / Assembler](#builder--assembler)
	* [Factory Method](#factory-method)
	* [Abstract Factory](#abstract-factory)
* [Behavioral Patterns](#behavioral)
	* [Observer / Listener](#observer--listener)
	* [Strategy](#strategy)
	* [Command](#command)
	* [State](#state)
	* [Chain of Responsibility](#chain-of-responsibility)
	* [Visitor](#visitor)
	* [Mediator](#mediator)
	* [Memento](#memento)
* [Structural Patterns](#structural)
	* [Adapter](#adapter)
	* [Decorator](#decorator)
	* [Facade](#facade)
	* [Protection Proxy](#protection-proxy)
	* [Composite](#composite)

Creational
==========
In software engineering, creational design patterns deal with creating objects in a way suitable to the situation, 
addressing potential design problems and complexity.


[Singleton](src/dev/shtanko/patterns/creational/singleton/Singleton.kt)
--------

Singleton pattern ensures that a class has only one instance and provides a global access point to that instance.

## Problem
* Global Access
* Resource Management: it helps manage resources that are shared and limited
* Lazy Initialization
* Thread Safety
* Memory Efficiency
* Centralized Configuration

## Pitfalls
* Global State
* Hidden Dependencies
* Testing Difficulty
* Concurrency Issues(in other languages)
* Lifecycle Management: Singletons typically live for the entire duration of the application,
which may not be desirable for resources
* Inflexibility: can restrict flexibility and extensibility because it tightly couples components
to a specific instance
* Difficulty in Dependency Injection

## Mitigations and Alternatives
* Dependency Injection (DI)
* Scoped Instances: limit the scope of singletons to specific modules or layers of the application where their global
access is necessary, rather than making them truly global
* Factory Pattern
* State Management Libraries

## When to use
* Singleton pattern should be used when we must ensure that only one instance of a class is created and when the
instance must be available through all the code

## Common usage
* Logger Classes
* Configuration Classes
* Accessing resources in shared mode

> [!IMPORTANT]
> objects are thread-safe in Kotlin

## Example
``` kotlin
interface Logger {
    fun info(message: String)
    fun debug(message: String)
    fun error(message: String)
}

object AppLogger : Logger {
    init {
        println("Logger initialized")
    }

    override fun info(message: String) {
        println("[INFO] $message")
    }

    override fun debug(message: String) {
        println("[DEBUG] $message")
    }

    override fun error(message: String) {
        println("[ERROR] $message")
    }
}
```

## Client code
``` kotlin
fun main() {
    val logger: Logger = AppLogger
    logger.info("Test info")
    logger.debug("Test debug")
    logger.error("Test error")
}
```

[Prototype](src/dev/shtanko/patterns/creational/prototype/Prototype.kt)
--------
A fully initialized instance to be copied or cloned.

## Intent
Prototype design pattern is a creational pattern that allows cloning of objects,
thereby hiding the complexities of creating new instances from the client.

## Problem
When creating new objects requires complex initialization or involves subclassing,
direct instantiation can be costly or not flexible enough.

## Pitfalls
* Cloning can be tricky if the objects being cloned have deep object graphs or
  circular references.
* Classes must implement `Cloneable` interface and provide proper cloning mechanisms.

## Mitigations and Alternatives
* Use a copy constructor or factory methods instead of `Cloneable` interface to create
  new objects.
* Consider using immutable objects where possible to avoid the need for cloning.

## When to Use
* Use the Prototype pattern when you want to hide the complexity of creating new
  instances from the client.
* When instances of a class can have multiple states or configurations and it is
  more convenient to clone an existing instance rather than creating a new one.

## Common Usage
* Creating objects that are expensive to instantiate repeatedly.
* Configuring complex objects using simpler prototypes.

> [!IMPORTANT]
> Kotlin's data classes have copy() method by default

## Example
``` kotlin
fun interface Prototype {
    operator fun invoke(): Prototype
}

/**
 * Kotlin's data classes have copy() method by default
 */
data class ConcretePrototype(val id: Int) : Prototype {
    override fun invoke(): Prototype = copy()
}
```

## Client code
``` kotlin
fun main() {
    val prototype = ConcretePrototype(1)
    val clonedPrototype = prototype.invoke()
    print(prototype !== clonedPrototype)
}
```

[Object Pool](src/dev/shtanko/patterns/creational/object_pool/ObjectPool.kt)
--------
Avoid expensive acquisition and release of resources by recycling objects that are no longer in use

## Intent
Object Pool design pattern is a creational pattern that manages a pool of
reusable objects, allowing efficient resource management and avoiding
expensive creation or destruction of objects.

## Problem
When creating and destroying objects frequently is expensive in terms of
time or resources, especially in resource-constrained environments.

## Pitfalls
* The pool management overhead can add complexity and potentially impact
  performance if not managed properly.
* Synchronization mechanisms are required for thread-safe access to objects
  in the pool.

## Mitigations and Alternatives
* Use lazy initialization or pre-creation strategies to balance between
  upfront resource consumption and on-demand allocation.
* Consider other patterns like Flyweight or Singleton for similar resource
  management needs.

## When to Use
* Use the Object Pool pattern when the cost of creating and destroying
  objects is high, and you want to reuse objects instead of creating new ones.
* When there are a limited number of resources (e.g., database connections,
  threads) available, and you want to manage them efficiently.

## Common Usage
* Managing database connection pools in web applications to handle multiple
  concurrent requests efficiently.
* Reusing thread objects in multithreaded applications to reduce overhead
  of thread creation and destruction.

## Example
``` kotlin
class ObjectPool(private val maxSize: Int) {
    private val pool: MutableList<Object> = mutableListOf()

    /**
     * Initializes the ObjectPool with [maxSize] number of objects.
     * Each object is instantiated with a unique identifier.
     */
    init {
        for (i in 1..maxSize) {
            pool.add(Object(i))
        }
    }

    /**
     * Retrieves an object from the pool.
     * @return An object from the pool, or `null` if the pool is empty.
     */
    fun getObject(): Object? = if (pool.isNotEmpty()) {
        pool.removeAt(0)
    } else {
        null
    }

    /**
     * Releases an object back to the pool.
     * @param obj The object to be released back to the pool.
     */
    fun releaseObject(obj: Object) {
        if (pool.size < maxSize) {
            pool.add(obj)
        }
    }
}
```

## Client code
``` kotlin
fun main() {
    val pool = ObjectPool(3)

    // Get objects from the pool
    val obj1 = pool.getObject()
    val obj2 = pool.getObject()
    val obj3 = pool.getObject()

    obj1?.let {
        println(it.process())
        pool.releaseObject(it)
    }

    obj2?.let {
        println(it.process())
        pool.releaseObject(it)
    }

    obj3?.let {
        println(it.process())
        pool.releaseObject(it)
    }
}
```

S.O.L.I.D.
==========

* [The Single Responsibility Principle](#-the-single-responsibility-principle)
* [The Open Closed Principle](#-the-open-closed-principle)
* [The Liskov Substitution Principle](#-the-liskov-substitution-principle)
* [The Interface Segregation Principle](#-the-interface-segregation-principle)
* [The Dependency Inversion Principle](#-the-dependency-inversion-principle)


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
class PodBayDoor : CanBeOpened, CanBeClosed {

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
class DoorOpener(private val door: CanBeOpened) {

    fun execute() {
        door.open()
    }

}

// I'm only responsible for closing, no idea what's inside or how to open.
class DoorCloser(private val door: CanBeClosed) {

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
class LaserBeam : CanShoot {
    override fun shoot(): String {
        return "Ziiiip!"
    }
}

// I have weapons and trust me I can fire them all at once. Boom! Boom! Boom!
class WeaponsComposite(var weapons: Array<CanShoot>) {
    fun shoot(): String {
        return weapons.map { it.shoot() }[0]
    }
}

val laser = LaserBeam()
var weapons = WeaponsComposite(weapons = arrayOf(laser))

weapons.shoot()

```
 
I'm a rocket launcher. I can shoot a rocket.
> ⚠️ To add rocket launcher support I don't need to change anything in existing classes.

```kotlin

class RocketLauncher : CanShoot {
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
```


# 🍴 The Interface Segregation Principle

Make fine grained interfaces that are client specific. ([read more](http://docs.google.com/a/cleancoder.com/viewer?a=v&pid=explorer&chrome=true&srcid=0BwhCYaYDn8EgOTViYjJhYzMtMzYxMC00MzFjLWJjMzYtOGJiMDc5N2JkYmJi&hl=en))

Example:
 
```kotlin


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

    fun fetchPayload(vehicle: PayloadHaving): String {
        return "Deployed ${vehicle.payload} at April 10, 2016, 11:23 UTC"
    }
}

class OfCourseIStillLoveYouBarge : LandingSiteHaving {
    override var landingSite = "a barge on the Atlantic Ocean"
}

class SpaceXCRS8 : Landing, PayloadHaving {

    override var payload = "BEAM and some Cube Sats"

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

val brewMachine = BrewMachine()
brewMachine.brew(Arabica())
brewMachine.brew(Rabusta())

```

Info
====

📖 Descriptions from: [ochococo/OOD-Principles-In-Swift](https://github.com/ochococo/OOD-Principles-In-Swift) Thank's
