package sku.processsor.generator.request.app.nested

import sku.processor.generator.request.GenerateRequest

@GenerateRequest
data class NestedWithin(
    val field: Int,
    val nested1: Nested1,
    val nested2: Nested2
) {

    @GenerateRequest
    data class Nested1(
        val nested11: Nested11
    ) {
        @GenerateRequest
        data class Nested11(
            val field11: Int
        )
    }

    @GenerateRequest
    data class Nested2(
        val nested1: Nested1,
        val nested11: Nested1.Nested11,
        val field2: Int
    )
}
