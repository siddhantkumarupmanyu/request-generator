package sku.processsor.generator.request.app.nested.in_another

data class ClassARequest (
val field: kotlin.Int,
val classB: sku.processsor.generator.request.app.nested.in_another.ClassBRequest,
val classBNested: sku.processsor.generator.request.app.nested.in_another.ClassBRequest.NestedRequest,
val classBNestedNotAnnotated: sku.processsor.generator.request.app.nested.in_another.ClassB.NotAnnotated,
val classC: sku.processsor.generator.request.app.nested.in_another.ClassC,
val classCNested: sku.processsor.generator.request.app.nested.in_another.ClassCRequest.NestedRequest,
)
{
}
