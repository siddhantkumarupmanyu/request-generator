package com.example

import com.example.processor.GenerateRequest

@GenerateRequest
data class Needed(
    val field1: String,
    val field2: String,
    val field3: String
)
