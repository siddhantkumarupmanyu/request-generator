package sku.processsor.generator.request.app

import sku.processor.generator.request.GenerateRequest
import java.util.*

@GenerateRequest
data class Excluded(
    val field1: Int,
    var field2: String,
    val field3: kotlin.Float,
    val date: Date,

    @GenerateRequest.Exclude
    val excludeThis: Int
)