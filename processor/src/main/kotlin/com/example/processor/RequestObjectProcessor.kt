package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class RequestObjectProcessor(
    private val codeGenerator: CodeGenerator
) : SymbolProcessor {

    var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) {
            return deferredSymbols()
        }

        val fileOutputStream = codeGenerator.createNewFile(Dependencies(false), "com.example", "SomeValueObjectRequest")

        val writer = OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)

        writer.write("package com.example \n")
        writer.write("data class SomeValueObjectRequest(val test1: String) \n")

        writer.close()

        invoked = true
        return deferredSymbols()
    }

    // for now, supposing we processed all the symbols
    private fun deferredSymbols(): List<KSAnnotated> = emptyList()

}