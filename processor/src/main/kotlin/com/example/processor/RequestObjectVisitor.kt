package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

// KSVisitorVoid is an adapter
class RequestObjectVisitor(private val codeGenerator: CodeGenerator) : KSVisitorVoid() {

    private var fileWriter = OutputStreamWriter(OutputStream.nullOutputStream())

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        // todo: handle if it's not a class 

        val fileName = classDeclaration.simpleName.asString() + "Request"

        // todo: handle nested data classes
        val fileStream =
            codeGenerator.createNewFile(Dependencies(false, classDeclaration.containingFile!!), "com.example", fileName)
        fileWriter = OutputStreamWriter(fileStream, StandardCharsets.UTF_8)

        // visit inside
        fileWriter.write("data class NeededRequest(val toWork: Int)")

        // close the fileWriter 
        fileWriter.close()
    }
}
