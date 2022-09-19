package com.example.outside

import com.example.processor.GenerateRequest

@GenerateRequest
data class ClassA(
    val field: Int,
    val classB: ClassB,
    val classC: ClassC
)
