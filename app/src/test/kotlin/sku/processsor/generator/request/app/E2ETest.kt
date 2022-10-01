package sku.processsor.generator.request.app

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.Test
import java.util.*

class E2ETest {

    @Test
    fun externalDependency() {
        val date = Date()

        val request = sku.processsor.generator.request.app.ExternalDependencyRequest(1, date)
        request.field = 2

        assertThat(request::class.isData).isTrue()
    }

    @Test
    fun inTheSamePackage() {
        val date = Date()
        val request = sku.processsor.generator.request.app.other.OtherPackageRequest(
            1,
            "2",
            3f,
            date
        )
        request.field2 = "var2"

        assertThat(request::class.isData).isTrue()
        assertThat(request::class.java.packageName).isEqualTo("sku.processsor.generator.request.app.other")
    }

    @Test
    fun excludeField() {
        val date = Date()
        val request = sku.processsor.generator.request.app.ExcludedRequest(
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
        val request1 = sku.processsor.generator.request.app.SameFileClass1Request(1)
        val request2 = sku.processsor.generator.request.app.SameFileClass2Request(1)
    }

    @Test
    fun otherClasses() {
        val classC = sku.processsor.generator.request.app.top_level.ClassC(3)

        val requestB = sku.processsor.generator.request.app.top_level.ClassBRequest(2)
        val requestA = sku.processsor.generator.request.app.top_level.ClassARequest(1, requestB, classC)
    }

    @Test
    fun outerClassNotAnnotated() {
        val nestedAnnotatedRequest =
            sku.processsor.generator.request.app.nested.OuterClassNotAnnotatedRequest.NestedAnnotatedRequest(1)

        // should not generate NestedNotAnnotatedRequest
        assertThatExceptionOfType(ClassNotFoundException::class.java).isThrownBy {
            Class.forName("ku.processsor.generator.request.app.nested.OuterClassNotAnnotatedRequest\$NestedNotAnnotatedRequest")
        }
    }

    @Test
    fun nestedInAnotherClass() {
        val nestedNotAnnotated = sku.processsor.generator.request.app.nested.in_another.ClassB.NotAnnotated(1)

        val bNestedRequest = sku.processsor.generator.request.app.nested.in_another.ClassBRequest.NestedRequest(1)
        val requestB = sku.processsor.generator.request.app.nested.in_another.ClassBRequest(1)

        val classC = sku.processsor.generator.request.app.nested.in_another.ClassC(1)
        val classCNestedRequest = sku.processsor.generator.request.app.nested.in_another.ClassCRequest.NestedRequest(1)

        val requestA =
            sku.processsor.generator.request.app.nested.in_another.ClassARequest(
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
        val nested11Request =
            sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested1Request.Nested11Request(1)
        val nested1Request =
            sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested1Request(nested11Request)

        val nested2Request = sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested2Request(
            nested1Request,
            nested11Request,
            2
        )

        val request = sku.processsor.generator.request.app.nested.NestedWithinRequest(
            1,
            nested1Request,
            nested2Request
        )
    }

}