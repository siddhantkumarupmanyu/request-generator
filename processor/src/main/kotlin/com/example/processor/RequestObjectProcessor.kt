package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

class RequestObjectProcessor(
    private val codeGenerator: CodeGenerator
) : SymbolProcessor {

    var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (invoked) {
            return deferredSymbols()
        }

        val files = resolver.getAllFiles()

        codeGenerator.createNewFile(Dependencies(false), "com.example", "SomeValueObjectRequest")

        invoked = true
        return deferredSymbols()
    }

    // for now, supposing we processed all the symbols
    private fun deferredSymbols(): List<KSAnnotated> = emptyList()

}