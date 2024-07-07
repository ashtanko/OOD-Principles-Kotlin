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

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class AppLoggerTest {

    companion object {
        private val standardOut = System.out
        private val outputStreamCaptor = ByteArrayOutputStream()

        @BeforeAll
        @JvmStatic
        fun setUp() {
            System.setOut(PrintStream(outputStreamCaptor))
        }

        @BeforeAll
        @JvmStatic
        fun tearDown() {
            System.setOut(standardOut)
        }
    }

    @Test
    fun `info logs correct message`() {
        AppLogger.info("Test info")
        assertTrue(outputStreamCaptor.toString().trim().contains("[INFO] Test info"))
    }

    @Test
    fun `debug logs correct message`() {
        AppLogger.debug("Test debug")
        assertTrue(outputStreamCaptor.toString().trim().contains("[DEBUG] Test debug"))
    }

    @Test
    fun `error logs correct message`() {
        AppLogger.error("Test error")
        assertTrue(outputStreamCaptor.toString().trim().contains("[ERROR] Test error"))
    }

    @Test
    fun `logger initialization message is logged once`() {
        val initialLogCount = outputStreamCaptor.toString().split("\n").count { it.contains("Logger initialized") }
        AppLogger.info("Another log")
        val subsequentLogCount = outputStreamCaptor.toString().split("\n").count { it.contains("Logger initialized") }
        assertTrue { initialLogCount == subsequentLogCount }
    }
}
