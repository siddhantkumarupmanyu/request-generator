package com.example.nested_in_another

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
