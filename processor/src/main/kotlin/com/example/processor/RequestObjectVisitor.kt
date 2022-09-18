package com.example.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

// KSVisitorVoid is an adapter
class RequestObjectVisitor(private val codeGenerator: CodeGenerator) : KSVisitorVoid() {

    private var fileWriter = OutputStreamWriter(OutputStream.nullOutputStream())

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        // todo: fail fast if not a data class

        val fileName = classDeclaration.simpleName.asString() + "Request"

        val sourceFile = classDeclaration.containingFile!!
        val fileStream =
            codeGenerator.createNewFile(
                Dependencies(false, sourceFile),
                sourceFile.packageName.toString(),
                fileName
            )
        fileWriter = OutputStreamWriter(fileStream, StandardCharsets.UTF_8)

        fileWriter.writeLine("package ${classDeclaration.packageName.asString()}")
        fileWriter.writeLine("")
        fileWriter.writeLine("data class ${fileName}(")

        val properties = classDeclaration.getAllProperties()
        properties.forEach {
            it.accept(this, Unit)
        }

        fileWriter.writeLine(")")
        fileWriter.close()
    }

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        val variableType = if (property.isMutable) "var" else "val"
        fileWriter.writeLine("$variableType ${property.simpleName.asString()}: ${property.type},")
    }

    private fun OutputStreamWriter.writeLine(str: String) {
        this.write("$str\n")
    }
}
