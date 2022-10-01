package com.example

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
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
        val request1 = SameFileClass1Request(1)
        val request2 = SameFileClass2Request(1)
    }

    @Test
    fun otherClasses() {
        val classC = com.example.top_level.ClassC(3)

        val requestB = com.example.top_level.ClassBRequest(2)
        val requestA = com.example.top_level.ClassARequest(1, requestB, classC)
    }

    @Test
    fun outerClassNotAnnotated() {
        val nestedAnnotatedRequest = com.example.nested.OuterClassNotAnnotatedRequest.NestedAnnotatedRequest(1)

        // should not generate NestedNotAnnotatedRequest
        assertThatExceptionOfType(ClassNotFoundException::class.java).isThrownBy {
            Class.forName("com.example.nested.OuterClassNotAnnotatedRequest\$NestedNotAnnotatedRequest")
        }
    }

    @Test
    fun nestedInAnotherClass() {
        val nestedNotAnnotated = com.example.nested.in_another.ClassB.NotAnnotated(1)

        val bNestedRequest = com.example.nested.in_another.ClassBRequest.NestedRequest(1)
        val requestB = com.example.nested.in_another.ClassBRequest(1)

        val classC = com.example.nested.in_another.ClassC(1)
        val classCNestedRequest = com.example.nested.in_another.ClassCRequest.NestedRequest(1)

        val requestA =
            com.example.nested.in_another.ClassARequest(
                1,
                requestB,
                bNestedRequest,
                nestedNotAnnotated,
                classC,
                classCNestedRequest
            )
    }

    @Test
    fun nestedWithin() {
        val nested11Request = com.example.nested.NestedWithinRequest.Nested1Request.Nested11Request(1)
        val nested1Request = com.example.nested.NestedWithinRequest.Nested1Request(nested11Request)

        val nested2Request = com.example.nested.NestedWithinRequest.Nested2Request(nested1Request, nested11Request, 2)

        val request = com.example.nested.NestedWithinRequest(1, nested1Request, nested2Request)
    }

}