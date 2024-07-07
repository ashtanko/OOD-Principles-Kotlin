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

package dev.shtanko.patterns.creational.object_pool

/**
 * Object to be managed by the pool
 * @param id The unique identifier of the object.
 */
class Object(private val id: Int) {
    fun process(): String = "Processing object $id"
}

/**
 * # Object Pool Design Pattern
 *
 * # Intent
 * Object Pool design pattern is a creational pattern that manages a pool of
 * reusable objects, allowing efficient resource management and avoiding
 * expensive creation or destruction of objects.
 *
 * # Problem
 * When creating and destroying objects frequently is expensive in terms of
 * time or resources, especially in resource-constrained environments.
 *
 * # Pitfalls
 * * The pool management overhead can add complexity and potentially impact
 *   performance if not managed properly.
 * * Synchronization mechanisms are required for thread-safe access to objects
 *   in the pool.
 *
 * # Mitigations and Alternatives
 * * Use lazy initialization or pre-creation strategies to balance between
 *   upfront resource consumption and on-demand allocation.
 * * Consider other patterns like Flyweight or Singleton for similar resource
 *   management needs.
 *
 * # When to use
 * * Use the Object Pool pattern when the cost of creating and destroying
 *   objects is high, and you want to reuse objects instead of creating new ones.
 * * When there are a limited number of resources (e.g., database connections,
 *   threads) available, and you want to manage them efficiently.
 *
 * # Common usage
 * * Managing database connection pools in web applications to handle multiple
 *   concurrent requests efficiently.
 * * Reusing thread objects in multithreaded applications to reduce overhead
 *   of thread creation and destruction.
 *
 * @param maxSize Maximum size of the object pool.
 */
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

// Client code
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
