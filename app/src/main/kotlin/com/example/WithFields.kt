package com.example

import com.example.processor.GenerateRequest

@GenerateRequest
data class WithFields(
    val field1: Int,
    val field2: String,
    val field3: Float
)