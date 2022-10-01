package com.example.nested

data class NestedWithin(
    val field: Int,
    val nested1: Nested1,
    val nested2: Nested2
) {

    data class Nested1(
        val nested11: Nested11
    ) {
        data class Nested11(
            val field11: Int
        )
    }

    data class Nested2(
        val nested1: Nested1,
        val nested11: Nested1.Nested11,
        val field2: Int
    )
}
