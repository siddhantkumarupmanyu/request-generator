package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class RequestObjectProcessor(
    private val codeGenerator: CodeGenerator
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val files = resolver.getAllFiles()

        for (file in files) {
            val name = file.fileName.removeSuffix(".kt") + "Request"
            
            val fileOutputStream = codeGenerator.createNewFile(Dependencies(false), "com.example", name)

            val writer = OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)

            writer.write("package com.example \n")
            writer.write("data class $name(val toWork: Int) \n")

            writer.close()
        }

        return deferredSymbols(files)
    }

    // https://kotlinlang.org/docs/ksp-multi-round.html#advanced
    private fun deferredSymbols(symbols: Sequence<KSAnnotated>): List<KSAnnotated> {
        return symbols.filter { !it.validate() }.toList()
    }

}