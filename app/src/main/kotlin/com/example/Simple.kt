package com.example

import com.example.processor.GenerateRequest
import java.util.*

@GenerateRequest
data class Simple(
    val field1: Int,
    var field2: String,
    val field3: kotlin.Float,
    val date: Date,

    @GenerateRequest.Exclude
    val excludeThis: Int
)