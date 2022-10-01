package com.example

import sku.processor.generator.request.GenerateRequest

@GenerateRequest
data class SameFileClass1(
    val field: Int
)

@GenerateRequest
data class SameFileClass2(
    val field: Int
)
