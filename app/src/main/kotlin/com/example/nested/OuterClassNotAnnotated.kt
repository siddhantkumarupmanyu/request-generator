package com.example.nested

import sku.processor.generator.request.GenerateRequest

data class OuterClassNotAnnotated(
    val field: Int
) {

    @GenerateRequest
    data class NestedAnnotated(
        val field: Int
    )
    
    data class NestedNotAnnotated(
        val field: Int
    )

}