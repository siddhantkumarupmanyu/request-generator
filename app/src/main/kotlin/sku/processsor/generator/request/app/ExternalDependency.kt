package sku.processsor.generator.request.app

import sku.processor.generator.request.GenerateRequest
import java.util.*

@GenerateRequest
data class ExternalDependency(
    var field: Int,
    val date: Date
)
