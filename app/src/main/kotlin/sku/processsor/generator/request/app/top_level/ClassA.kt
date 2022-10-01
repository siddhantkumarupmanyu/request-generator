package sku.processsor.generator.request.app.top_level

import sku.processor.generator.request.GenerateRequest

@GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classC: ClassC
)
