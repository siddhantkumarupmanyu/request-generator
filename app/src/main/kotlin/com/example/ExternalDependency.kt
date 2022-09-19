package com.example

import com.example.processor.GenerateRequest
import java.util.*

@GenerateRequest
data class ExternalDependency(
    var field: Int,
    val date: Date
)
