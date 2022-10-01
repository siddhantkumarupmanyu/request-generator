package com.example.nested.in_another

import com.example.processor.GenerateRequest

data class ClassC(
    val field: Int,
) {

    @GenerateRequest
    data class Nested(
        val field: Int
    )

}