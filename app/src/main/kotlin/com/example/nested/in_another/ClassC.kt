package com.example.nested.in_another

import sku.processor.generator.request.GenerateRequest

data class ClassC(
    val field: Int,
) {

    @GenerateRequest
    data class Nested(
        val field: Int
    )

}