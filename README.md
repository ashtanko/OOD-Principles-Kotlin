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

```swift

val doorOpener = DoorOpener(door)
doorOpener.execute()

```
 
> ⚠ If another operation should be made upon closing the door,
> like switching on the alarm, you don't have to change the `DoorOpener` class.

```swift

val doorCloser = DoorCloser(door)
doorCloser.execute()

```