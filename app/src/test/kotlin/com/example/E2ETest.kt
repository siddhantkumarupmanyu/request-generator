package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class E2ETest {

    @Test
    fun generateWithCorrectPackage() {
        val request = NeededRequest(1)

        // assertThat()
        // val request2 = AnotherNeededRequest(1)

        assertThat(request::class.isData).isTrue()
        assertThat(request::class.java.packageName).isEqualTo("com.example")
    }

    // test multiple classes in same file
    // fields are generated
    // excluding fields
    // nested fields => getSymbolsWithAnnotation with true
    // nested classes with nested fields => needs to fix creation of file logic...
    //      this also means nest inside the request object. same structure
}