package com.example.nested_in_another


// @GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classBNested: ClassB.ClassBNested,
    val classBNested2: ClassB.ClassBNoRequest
)
