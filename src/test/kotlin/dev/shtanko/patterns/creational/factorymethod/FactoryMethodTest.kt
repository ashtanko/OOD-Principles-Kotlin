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

package dev.shtanko.patterns.creational.factorymethod

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FactoryMethodTest {
    @Test
    fun `WordDocumentCreator creates a WordDocument`() {
        val creator = WordDocumentCreator()
        val document = creator.createDocument()
        assert(document is WordDocument)
    }

    @Test
    fun `PDFDocumentCreator creates a PdfDocument`() {
        val creator = PDFDocumentCreator()
        val document = creator.createDocument()
        assert(document is PdfDocument)
    }

    @Test
    fun `TextDocumentCreator creates a TextDocument`() {
        val creator = TextDocumentCreator()
        val document = creator.createDocument()
        assert(document is TextDocument)
    }

    @Test
    fun `WordDocument open, save, close operations`() {
        val document = WordDocument
        assertEquals("Opening Word document", document.open())
        assertEquals("Saving Word document", document.save())
        assertEquals("Closing Word document", document.close())
    }

    @Test
    fun `PdfDocument open, save, close operations`() {
        val document = PdfDocument
        assertEquals("Opening PDF document", document.open())
        assertEquals("Saving PDF document", document.save())
        assertEquals("Closing PDF document", document.close())
    }

    @Test
    fun `TextDocument open, save, close operations`() {
        val document = TextDocument
        assertEquals("Opening Text document", document.open())
        assertEquals("Saving Text document", document.save())
        assertEquals("Closing Text document", document.close())
    }
}
