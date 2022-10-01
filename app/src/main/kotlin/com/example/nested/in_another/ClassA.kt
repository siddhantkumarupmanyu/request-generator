package com.example.nested.in_another

import com.example.processor.GenerateRequest


@GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classBNested: ClassB.Nested,
    val classBNestedNotAnnotated: ClassB.NotAnnotated,
    val classC: ClassC,
    // val classCNested: ClassC.Nested // todo: use outer class not annotated, dry them
)
