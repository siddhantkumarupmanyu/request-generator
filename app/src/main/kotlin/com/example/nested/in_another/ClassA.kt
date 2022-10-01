package com.example.nested.in_another


// @GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classBNested: ClassB.ClassBNested,
    val classBNested2: ClassB.ClassBNoRequest
)
