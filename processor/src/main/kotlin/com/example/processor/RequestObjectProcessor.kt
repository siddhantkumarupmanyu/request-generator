package com.example.processor

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate

class RequestObjectProcessor(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotatedClasses = resolver.getSymbolsWithAnnotation(GenerateRequest::class.qualifiedName!!)
        val files = annotatedClasses.map {
            it.containingFile!!
        }.toSet().asSequence()
        val requestObjectVisitor = RequestObjectVisitor(codeGenerator)
        visitValidSymbols(files, requestObjectVisitor)

        return deferredSymbols(annotatedClasses)
    }

    private fun <T : KSAnnotated> visitValidSymbols(symbols: Sequence<T>, visitor: RequestObjectVisitor) {
        symbols.forEach {
            if (it.validate()) {
                it.accept(visitor, Unit)
            }
        }
    }

    // https://kotlinlang.org/docs/ksp-multi-round.html#advanced
    private fun deferredSymbols(symbols: Sequence<KSAnnotated>): List<KSAnnotated> {
        return symbols.filter { !it.validate() }.toList()
    }

}