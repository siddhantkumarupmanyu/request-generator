package sku.processsor.generator.request.app.nested.in_another

import sku.processor.generator.request.GenerateRequest

@GenerateRequest
data class ClassB(
    val field: Int
) {

    @GenerateRequest
    data class Nested(
        val fieldNested: Int
    )

    data class NotAnnotated(
        val toWork: Int
    )

}
