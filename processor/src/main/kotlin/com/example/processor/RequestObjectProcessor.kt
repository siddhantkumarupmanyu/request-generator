package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import java.io.OutputStreamWriter

class RequestObjectProcessor(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    var no: Int = 0

    override fun process(resolver: Resolver): List<KSAnnotated> {
        // todo: this is giving me nested classes too, but i want to generate nested classes in tested class
        // so, i need get the set(set not list) of files
        // and then pass that to by visitor and it will act on that file.
        // file will be the base instead of class what is right now.
        val symbols = resolver.getSymbolsWithAnnotation(GenerateRequest::class.qualifiedName!!)

        val requestObjectVisitor = RequestObjectVisitor(codeGenerator)
        visitValidSymbols(symbols, requestObjectVisitor)

        return deferredSymbols(symbols)
    }

    private fun visitValidSymbols(symbols: Sequence<KSAnnotated>, visitor: RequestObjectVisitor) {
        symbols.forEach {
            if (it.validate()) {
                it.accept(visitor, Unit)

                if (it is KSClassDeclaration) {
                    val outputStream = codeGenerator.createNewFile(Dependencies.ALL_FILES, "com.example", "test_${it.qualifiedName!!.asString()}")
                    val outputStreamWriter = OutputStreamWriter(outputStream)
                    outputStreamWriter.write("n = $no \n")
                    outputStreamWriter.write(it.qualifiedName!!.asString() + "\n")
                    outputStreamWriter.flush()
                    outputStreamWriter.close()

                }

            }
        }
        no += 1
    }

    // https://kotlinlang.org/docs/ksp-multi-round.html#advanced
    private fun deferredSymbols(symbols: Sequence<KSAnnotated>): List<KSAnnotated> {
        return symbols.filter { !it.validate() }.toList()
    }

}