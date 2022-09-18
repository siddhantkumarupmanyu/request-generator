package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class E2ETest {

    @Test
    fun simpleDataClass() {
        val date = Date()
        val request = SimpleRequest(
            1,
            "2",
            3f,
            date
        )
        request.field2 = "var2"

        assertThat(request::class.isData).isTrue()
        assertThat(request::class.java.packageName).isEqualTo("com.example")
    }


    // test multiple classes in same file
    // nested fields with classes outside => need to change the type to `${type}Request` 
    // nested classes with nested fields => needs to fix creation of file logic...
    //      this also means nest inside the request object. same structure
    // fields with default values

    // when visiting i could some data too. that would make things easier esp multiple classes in same file
    // idk will see.
    // tbh imo, my visitor do not even need to create file we will create all the required files beforehand
    // my visitor will just write to those files. that makes sense.
    // instead of code generator we will pass the fileWrite, let's see
}