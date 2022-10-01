package com.example

import sku.processor.generator.request.GenerateRequest
import java.util.*

@GenerateRequest
data class ExternalDependency(
    var field: Int,
    val date: Date
)
