package com.example.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

// KSVisitorVoid is an adapter
class RequestObjectVisitor(
    private val codeGenerator: CodeGenerator,
) : KSVisitorVoid() {

    private var fileWriter = OutputStreamWriter(OutputStream.nullOutputStream())

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        // todo: fail fast if not a data class

        val fileName = classDeclaration.simpleName.asString() + "Request"

        val sourceFile = classDeclaration.containingFile!!
        val fileStream =
            codeGenerator.createNewFile(
                Dependencies(false, sourceFile),
                sourceFile.packageName.asString(),
                fileName
            )
        fileWriter = OutputStreamWriter(fileStream, StandardCharsets.UTF_8)

        fileWriter.writeLine("package ${classDeclaration.packageName.asString()}")
        fileWriter.writeLine("")

        classDeclaration.typeParameters.forEach {
            fileWriter.writeLine(it.simpleName.asString())
        }

        fileWriter.writeLine("data class ${fileName}(")

        val properties = classDeclaration.getAllProperties()
        properties.forEach {
            it.accept(this, Unit)
        }

        fileWriter.writeLine(")")
        fileWriter.close()
    }

    @OptIn(KspExperimental::class)
    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        if (property.isAnnotationPresent(GenerateRequest.Exclude::class)) return

        val variableType = if (property.isMutable) "var" else "val"

        val declaration = property.type.resolve().declaration
        require(declaration is KSClassDeclaration) { "declaration should be a class" }
        val fullyQualifiedType = getFullQualifiedName(declaration)

        fileWriter.writeLine("$variableType ${property.simpleName.asString()}: ${fullyQualifiedType},")
    }

    private fun OutputStreamWriter.writeLine(str: String) {
        this.write("$str\n")
    }

    private fun getFullQualifiedName(declaration: KSClassDeclaration): String {
        if (isRoot(declaration)) {
            return declaration.qualifiedName!!.asString()
        } else {
            val parent = declaration.parentDeclaration
            require(parent is KSClassDeclaration) { "parent declaration should be a class" }
            return getFullQualifiedName(parent) + "Request"
        }
    }

    @OptIn(KspExperimental::class)
    private fun isRoot(declaration: KSClassDeclaration): Boolean {
        if (!declaration.isAnnotationPresent(GenerateRequest::class)) return true
        return declaration.parentDeclaration == null
    }
}
