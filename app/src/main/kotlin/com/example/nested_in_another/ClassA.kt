package com.example.nested_in_another

import com.example.processor.GenerateRequest

@GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classBNested: ClassB.ClassBNested,
    val classBNested2: ClassB.ClassBNoRequest
)
