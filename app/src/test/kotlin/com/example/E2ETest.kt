package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class E2ETest {

    @Test
    fun generatesWithCorrectPackage() {
        val request = WithSingleFieldRequest(1)

        assertThat(request::class.isData).isTrue()
        assertThat(request::class.java.packageName).isEqualTo("com.example")
    }

    @Test
    fun generatedRequiredFields() {

    }

    // test multiple classes in same file
    // fields are generated
    // excluded fields not generated
    // nested fields with classes outside => need to change the type to `${type}Request` 
    // nested classes with nested fields => needs to fix creation of file logic...
    //      this also means nest inside the request object. same structure
    // fields with default values
}