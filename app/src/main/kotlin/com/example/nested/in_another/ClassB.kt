package com.example.nested.in_another

import com.example.processor.GenerateRequest

// @GenerateRequest
data class ClassB(
    val field: Int
) {

    // @GenerateRequest
    data class ClassBNested(
        val fieldNested: Int
    )

    data class ClassBNoRequest(
        val toWork: Int
    )

}
