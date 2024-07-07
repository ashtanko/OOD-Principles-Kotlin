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

/**
 * # Prototype Design Pattern
 *
 * # Intent
 * Prototype design pattern is a creational pattern that allows cloning of objects,
 * thereby hiding the complexities of creating new instances from the client.
 *
 * # Problem
 * When creating new objects requires complex initialization or involves subclassing,
 * direct instantiation can be costly or not flexible enough.
 *
 * # Pitfalls
 * * Cloning can be tricky if the objects being cloned have deep object graphs or
 *   circular references.
 * * Classes must implement cloneable interface and provide proper cloning mechanisms.
 *
 * # Mitigations and Alternatives
 * * Use a copy constructor or factory methods instead of cloneable interface to create
 *   new objects.
 * * Consider using immutable objects where possible to avoid the need for cloning.
 *
 * # When to use
 * * Use the Prototype pattern when you want to hide the complexity of creating new
 *   instances from the client.
 * * When instances of a class can have multiple states or configurations and it is
 *   more convenient to clone an existing instance rather than creating a new one.
 *
 * # Common usage
 * * Creating objects that are expensive to instantiate repeatedly.
 * * Configuring complex objects using simpler prototypes.
 */
fun interface Prototype {
    operator fun invoke(): Prototype
}

/**
 * Kotlin's data classes have copy() method by default
 *
 * @property id An integer representing the unique identifier of the prototype.
 */
data class ConcretePrototype(val id: Int) : Prototype {
    override fun invoke(): Prototype = copy()
}

fun main() {
    val prototype = ConcretePrototype(1)
    val clonedPrototype = prototype.invoke()
    print(prototype !== clonedPrototype)
}
