package com.example

import com.example.processor.GenerateRequest

@GenerateRequest
data class SameFileClass1(
    val field: Int
)

@GenerateRequest
data class SameFileClass2(
    val field: Int
)
