package com.example.nested

import com.example.processor.GenerateRequest

data class OuterClassNotAnnotated(
    val field: Int
) {

    @GenerateRequest
    data class NestedAnnotated(
        val field: Int
    )

}