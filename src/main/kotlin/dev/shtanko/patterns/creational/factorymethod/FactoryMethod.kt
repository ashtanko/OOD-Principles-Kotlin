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

/**
 * # Factory Method Design Pattern
 *
 * # Intent
 * Define an interface for creating an object, but let subclasses alter the type
 * of objects that will be created.
 *
 * # Problem
 * * You need to create objects without specifying the exact class of object
 * that will be created.
 * * You want to allow a class to defer instantiation to subclasses.
 *
 * # Pitfalls
 * * The code can become more complex due to the additional classes and
 * interfaces.
 * * It can be more challenging to understand and maintain if overused or
 * misused.
 *
 * # Mitigations and Alternatives
 * * Use simpler creation methods if the problem domain is not complex enough to
 * require a factory method.
 * * Consider using other creational patterns like Abstract Factory or Builder
 * for more complex scenarios.
 *
 * # When to use
 * * When a class can't anticipate the type of objects it needs to create
 * beforehand.
 * * When you want to delegate the responsibility of instantiation to subclasses
 * * When you need to provide a way to extend and include more types without
 * changing the code that uses the interface.
 *
 * # Common usage
 * * GUI frameworks where the exact type of components to create depends on the
 * specific look and feel.
 * * Logging frameworks where different logging targets (e.g., console, file,
 * network) are chosen at runtime.
 */
interface Document {
    fun open()
    fun close()
    fun save()
}

data object WordDocument : Document {
    override fun open() {
        println("Opening Word document")
    }

    override fun close() {
        println("Closing Word document")
    }

    override fun save() {
        println("Saving Word document")
    }
}

data object PdfDocument : Document {
    override fun open() {
        println("Opening PDF document")
    }

    override fun close() {
        println("Closing PDF document")
    }

    override fun save() {
        println("Saving PDF document")
    }
}

data object TextDocument : Document {
    override fun open() {
        println("Opening Text document")
    }

    override fun close() {
        println("Closing Text document")
    }

    override fun save() {
        println("Saving Text document")
    }
}

/**
 * Abstract class representing a creator in the Factory Method design pattern.
 * This class declares the factory method `createDocument` that is supposed to
 * return an instance of a class that implements the `Document` interface.
 * It also provides a concrete method `newDocument` that creates a new document,
 * opens it, and returns it.
 * Subclasses are expected to implement the `createDocument` method to
 * instantiate documents of specific types.
 */
abstract class DocumentCreator {
    /**
     * Abstract factory method that subclasses must implement to produce
     * specific types of documents.
     * @return An instance of a class that implements the `Document` interface.
     */
    abstract fun createDocument(): Document

    /**
     * Creates a new document using the factory method, opens it, and returns it
     * This method demonstrates the use of the Factory Method pattern to
     * delegate the creation of objects to subclasses,
     * while still providing some common functionality
     * (like opening the document) in the base class.
     * @return The newly created and opened document.
     */
    fun newDocument(): Document {
        val document = createDocument()
        document.open()
        return document
    }
}

class WordDocumentCreator : DocumentCreator() {
    override fun createDocument(): Document = WordDocument
}

class PDFDocumentCreator : DocumentCreator() {
    override fun createDocument(): Document = PdfDocument
}

class TextDocumentCreator : DocumentCreator() {
    override fun createDocument(): Document = TextDocument
}

/**
 * In this example:
 *
 * - **Document Interface**: Defines the operations (`open`, `close`, `save`)
 * that any document should support.
 * - **Concrete Document Classes**: `WordDocument`, `PDFDocument`, and
 * `TextDocument` implement the `Document` interface.
 * - **DocumentCreator Abstract Class**: Provides a factory method
 * `createDocument` which is overridden by concrete creators to produce specific
 * types of documents.
 * - **Concrete Document Creators**: `WordDocumentCreator`, `PDFDocumentCreator`
 * and `TextDocumentCreator` override the `createDocument` method to instantiate
 * specific document types.
 * - **Main Function**: Demonstrates how different document creators are used to
 * create and manipulate different types of documents.
 *
 */
fun main() {
    println("App: Launched with the WordDocumentCreator.")
    val wordCreator = WordDocumentCreator()
    val wordDoc: Document = wordCreator.newDocument()
    wordDoc.save()
    wordDoc.close()

    println("App: Launched with the PDFDocumentCreator.")
    val pdfCreator = PDFDocumentCreator()
    val pdfDoc: Document = pdfCreator.newDocument()
    pdfDoc.save()
    pdfDoc.close()

    println("App: Launched with the TextDocumentCreator.")
    val textCreator = TextDocumentCreator()
    val textDoc: Document = textCreator.newDocument()
    textDoc.save()
    textDoc.close()
}
