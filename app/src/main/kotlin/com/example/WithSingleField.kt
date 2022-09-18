package com.example

import com.example.processor.GenerateRequest

@GenerateRequest
data class WithSingleField(
    val forDataClass: Int
)
