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

package dev.shtanko.patterns.creational.prototype

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrototypeTest {
    @Test
    fun `clone of prototype has same class type`() {
        val prototype = ConcretePrototype(1)
        val clonedPrototype = prototype.invoke()
        assertEquals(prototype.javaClass, clonedPrototype.javaClass)
    }

    @Test
    fun `clone of prototype has different identity`() {
        val prototype = ConcretePrototype(1)
        val clonedPrototype = prototype.invoke()
        assert(prototype !== clonedPrototype)
    }

    @Test
    fun `clone of prototype preserves properties`() {
        val prototype = ConcretePrototype(1)
        val clonedPrototype = prototype.invoke() as ConcretePrototype
        assertEquals(prototype.id, clonedPrototype.id)
    }

    @Test
    fun `cloning modifies property successfully`() {
        val prototype = ConcretePrototype(1)
        val modifiedClone = (prototype.invoke() as ConcretePrototype).copy(id = 2)
        assertEquals(2, modifiedClone.id)
    }
}
