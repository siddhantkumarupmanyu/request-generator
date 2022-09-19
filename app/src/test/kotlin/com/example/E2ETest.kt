package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import java.util.*

class E2ETest {

    @Test
    fun externalDependency() {
        val date = Date()
        
        val request = ExternalDependencyRequest(1, date)
        request.field = 2

        assertThat(request::class.isData).isTrue()
    }

    @Test
    fun inTheSamePackage() {
        val date = Date()
        val request = com.example.other.OtherPackageRequest(
            1,
            "2",
            3f,
            date
        )
        request.field2 = "var2"

        assertThat(request::class.isData).isTrue()
        assertThat(request::class.java.packageName).isEqualTo("com.example.other")
    }

    @Test
    fun excludeField() {
        val date = Date()
        val request = ExcludedRequest(
            1,
            "2",
            3f,
            date
            // excludeThis being excluded
        )
        request.field2 = "var2"

        assertThat(request::class.isData).isTrue()
    }

    @Test
    fun multipleClassesInSameFile() {
        // in terms of compiled code.
        // for classes, it does not matter.
        // but for top level functions it generates a Kt class
        // so generating different files for each class no matter how they are declared

        val request1 = SameFileClass1Request(1)
        val request2 = SameFileClass2Request(1)
    }

    @Test
    fun classReferenceOtherAnnotatedClasses_Outside() {
        val classC = com.example.outside.ClassC(3)

        val requestB = com.example.outside.ClassBRequest(2)
        val requestA = com.example.outside.ClassARequest(1, requestB, classC)
    }

    @Ignore
    @Test
    fun classReferenceOtherAnnotatedClasses_Nested() {

    }


    // nested fields with classes outside => need to change the type to `${type}Request` 
    // nested classes with nested fields => needs to fix creation of file logic...
    //      this also means nest inside the request object. same structure

    // when visiting i could some data too. that would make things easier esp multiple classes in same file
    // idk will see.
    // tbh imo, my visitor do not even need to create file we will create all the required files beforehand
    // my visitor will just write to those files. that makes sense.
    // instead of code generator we will pass the fileWrite, let's see
}