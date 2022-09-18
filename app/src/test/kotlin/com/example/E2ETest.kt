package com.example

import NeededRequest
import org.junit.Test

class E2ETest {

    @Test
    fun generateRequestObject() {
        val request = NeededRequest(1)
        
        // assertThat()
        // val request2 = AnotherNeededRequest(1)
    }

    // only annotated ones are generated    
    // test multiple classes in same file
    // different packages
    // fields are generated
    // excluding fields
    // nested fields => getSymbolsWithAnnotation with true
    // nested classes with nested fields => needs to fix creation of file logic...
    //      this also means nest inside the request object. same structure
}