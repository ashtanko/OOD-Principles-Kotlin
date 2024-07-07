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

/**
 * The Single Responsibility Principle
 */
interface CanBeOpened {
    fun open()
}

interface CanBeClosed {
    fun closed()
}

class PodBayDoor : CanBeOpened, CanBeClosed {
    private var state: State = State.CLOSED

    override fun open() {
        state = State.OPEN
    }

    override fun closed() {
        state = State.CLOSED
    }

    private enum class State {
        CLOSED, OPEN,
    }
}

/**
 * Represents a door opener that can open a door.
 * @param door The object that can be opened.
 */
class DoorOpener(private val door: CanBeOpened) {
    /**
     * Executes the action of opening the door.
     */
    fun execute() {
        door.open()
    }
}

/**
 * Represents a door closer that can close a door.
 * @param door The object that can be closed.
 */
class DoorCloser(private val door: CanBeClosed) {
    /**
     * Executes the action of closing the door.
     */
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
