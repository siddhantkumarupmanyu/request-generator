package sku.processor.generator.request

import sku.processor.generator.request.Utils.writeLine
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

// KSVisitorVoid is an adapter
@OptIn(KspExperimental::class)
class RequestObjectVisitor(
    private val codeGenerator: CodeGenerator,
) : KSVisitorVoid() {

    private var fileWriter = OutputStreamWriter(OutputStream.nullOutputStream())

    override fun visitFile(file: KSFile, data: Unit) {
        val classDeclarations = file.declarations

        val fileName = stringWithDotKtRemoved(file.fileName) + "Request"

        val fileStream =
            codeGenerator.createNewFile(
                Dependencies(false, file),
                file.packageName.asString(),
                fileName
            )

        fileWriter = OutputStreamWriter(fileStream, StandardCharsets.UTF_8)
        fileWriter.writeLine("package ${file.packageName.asString()}")

        classDeclarations.forEach {
            it.accept(this, Unit)
        }

        fileWriter.close()
    }


    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        // todo: fail fast if not a data class
        fileWriter.writeLine("")

        fileWriter.writeLine("data class ${classDeclaration.simpleName.asString()}Request (")

        val properties = classDeclaration.getAllProperties()
        properties.forEach {
            it.accept(this, Unit)
        }

        fileWriter.writeLine(")")

        fileWriter.writeLine("{")

        val nestedClasses =
            classDeclaration.declarations.fold(listOf<KSClassDeclaration>()) { classes, declaration ->
                if (declaration is KSClassDeclaration) {
                    classes + declaration
                } else {
                    classes
                }
            }

        nestedClasses.forEach {
            if (it.isAnnotationPresent(GenerateRequest::class)) {
                it.accept(this, Unit)
            }
        }

        fileWriter.writeLine("}")
    }

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        if (property.isAnnotationPresent(GenerateRequest.Exclude::class)) return

        val variableType = if (property.isMutable) "var" else "val"

        val declaration = property.type.resolve().declaration
        require(declaration is KSClassDeclaration) { "declaration should be a class" }
        val fullyQualifiedType = getFullQualifiedName(declaration)

        fileWriter.writeLine("$variableType ${property.simpleName.asString()}: ${fullyQualifiedType},")
    }

    private fun getFullQualifiedName(declaration: KSClassDeclaration): String {
        if (!declaration.isAnnotationPresent(GenerateRequest::class)) return declaration.qualifiedName!!.asString()

        fun nameForNestedAndRoot(declaration: KSClassDeclaration): String {
            if (isRoot(declaration)) {
                return declaration.qualifiedName!!.asString() + "Request"
            } else {
                val parent = declaration.parentDeclaration
                require(parent is KSClassDeclaration) { "parent declaration should be a class" }
                return nameForNestedAndRoot(parent) + "." + declaration.simpleName.asString() + "Request"
            }
        }

        return nameForNestedAndRoot(declaration)
    }

    private fun isRoot(declaration: KSClassDeclaration): Boolean {
        return declaration.parentDeclaration == null
    }

    // since this actually doesn't affect the generated .class file name?
    // so, i don't have test for this rn. 
    private fun stringWithDotKtRemoved(fileName: String) = fileName.dropLast(3)
}
