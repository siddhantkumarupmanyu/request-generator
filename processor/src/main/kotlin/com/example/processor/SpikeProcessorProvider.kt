package com.example.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated

class SpikeProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return SpikeProcessor(environment.logger)
    }
}

class SpikeProcessor(private val logger: KSPLogger) : SymbolProcessor {

    val functions = mutableListOf<String>()


    override fun process(resolver: Resolver): List<KSAnnotated> {
        val files = resolver.getAllFiles()

        logger.warn("hello processor")

        for (file in files) {
            logger.warn(file.filePath)
        }

        return emptyList()
    }

}