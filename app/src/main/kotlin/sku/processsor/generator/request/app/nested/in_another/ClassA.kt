package sku.processsor.generator.request.app.nested.in_another

import sku.processor.generator.request.GenerateRequest


@GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classBNested: ClassB.Nested,
    val classBNestedNotAnnotated: ClassB.NotAnnotated,
    val classC: ClassC,
    val classCNested: ClassC.Nested
)
