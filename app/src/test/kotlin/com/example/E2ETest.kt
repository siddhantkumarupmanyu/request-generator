package com.example

import org.junit.Test

class E2ETest {

    @Test
    fun generateRequestObject() {
        val request = NeededRequest(1)
        val request2 = AnotherNeededRequest(1)
    }

    // only annotated ones are generated    
    // test multiple classes in same file
    // different packages
    // fields are generated
    // nested fields
}