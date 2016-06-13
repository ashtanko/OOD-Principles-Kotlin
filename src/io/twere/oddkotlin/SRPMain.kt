package io.twere.oddkotlin

/**
 * Created by adamluissean on 09.06.16.
 */

interface CanBeOpened {
    fun open()
}

interface CanBeClosed {
    fun closed()
}

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

class DoorOpener(door: CanBeOpened) {

    val door: CanBeOpened = door

    fun execute() {
        door.open()
    }
}

class DoorCloser(door: CanBeClosed) {
    val door: CanBeClosed = door

    fun execute() {
        door.closed()
    }
}

fun main(args: Array<String>) {
    val door = PodBayDoor()

    val doorOpener = DoorOpener(door)
    doorOpener.execute()

    val doorCloser = DoorCloser(door)
    doorCloser.execute()
}
