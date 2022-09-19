package com.example.other

import com.example.processor.GenerateRequest
import java.util.*

@GenerateRequest
data class OtherPackage(
    val field1: Int,
    var field2: String,
    val field3: kotlin.Float,
    val date: Date
)