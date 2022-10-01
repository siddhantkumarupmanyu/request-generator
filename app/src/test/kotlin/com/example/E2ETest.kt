package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.fail
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
    //
    // @Test
    // fun classReferenceOtherAnnotatedClasses_TopLevel() {
    //     val classC = com.example.top_level.ClassC(3)
    //
    //     val requestB = com.example.top_level.ClassBRequest(2)
    //     val requestA = com.example.top_level.ClassARequest(1, requestB, classC)
    // }

    // @Test
    // fun classReferenceOtherAnnotatedClasses_NestedInAnother() {
    //     val requestBNested = com.example.nested_in_another.ClassBRequest.ClassBNestedRequest(2)
    //
    //     val requestB = com.example.nested_in_another.ClassBRequest(1)
    //     val requestA = com.example.nested_in_another.ClassARequest(1, requestB, requestBNested)
    // }

    @Ignore
    @Test
    fun classReferenceOtherAnnotatedClasses_NestedInItself() {

    }

    @Ignore
    @Test
    fun topLevelClassNotAnnotated() {
        // need to see...
    }

}