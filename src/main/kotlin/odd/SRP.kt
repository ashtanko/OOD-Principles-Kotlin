package odd

/**
 * The Single Responsibility Principle
 * Created by adamluissean on 09.06.16.
 */

interface CanBeOpened {
    fun open()
}

interface CanBeClosed {
    fun closed()
}

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

class DoorOpener(private val door: CanBeOpened) {

    fun execute() {
        door.open()
    }
}

class DoorCloser(private val door: CanBeClosed) {

    fun execute() {
        door.closed()
    }
}

fun main() {
    val door = PodBayDoor()

    val doorOpener = DoorOpener(door)
    doorOpener.execute()

    val doorCloser = DoorCloser(door)
    doorCloser.execute()
}
