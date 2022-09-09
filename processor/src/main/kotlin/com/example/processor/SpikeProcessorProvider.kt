package com.example.processor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated

class SpikeProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return SpikeProcessor()
    }
}

class SpikeProcessor() : SymbolProcessor {

    val functions = mutableListOf<String>()


    override fun process(resolver: Resolver): List<KSAnnotated> {
        val files = resolver.getAllFiles()

        for (file in files) {
            println(file)
        }

        return emptyList()
    }

}