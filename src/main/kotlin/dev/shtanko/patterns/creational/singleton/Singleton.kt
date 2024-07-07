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

package dev.shtanko.patterns.creational.singleton

/**
 * # Singleton Design Pattern
 *
 * # Intent
 * Singleton pattern ensures that a class has only one instance and provides a global access point to that instance
 *
 * # Problem
 * * Global Access
 * * Resource Management: it helps manage resources that are shared and limited
 * * Lazy Initialization
 * * Thread Safety
 * * Memory Efficiency
 * * Centralized Configuration
 *
 * # Pitfalls
 * * Global State
 * * Hidden Dependencies
 * * Testing Difficulty
 * * Concurrency Issues(in other languages)
 * * Lifecycle Management: Singletons typically live for the entire duration of the application,
 * which may not be desirable for resources
 * * Inflexibility: can restrict flexibility and extensibility because it tightly couples components
 * to a specific instance
 * * Difficulty in Dependency Injection
 *
 * # Mitigations and Alternatives
 * * Dependency Injection (DI)
 * * Scoped Instances: limit the scope of singletons to specific modules or layers of the application where their global
 * access is necessary, rather than making them truly global
 * * Factory Pattern
 * * State Management Libraries
 *
 * # When to use
 * * Singleton pattern should be used when we must ensure that only one instance of a class is created and when the
 * instance must be available through all the code
 *
 * # Common usage
 * * Logger Classes
 * * Configuration Classes
 * * Accesing resources in shared mode
 */
interface Logger {
    fun info(message: String)
    fun debug(message: String)
    fun error(message: String)
}

/**
 * Note: object is thread safe in Kotlin
 */
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

fun main() {
    val logger: Logger = AppLogger
    logger.info("Test info")
    logger.debug("Test debug")
    logger.error("Test error")
}
