package sku.processsor.generator.request.app.nested.in_another

data class ClassARequest (
val field: kotlin.Int,
val classB: com.example.nested.in_another.ClassBRequest,
val classBNested: com.example.nested.in_another.ClassBRequest.NestedRequest,
val classBNestedNotAnnotated: com.example.nested.in_another.ClassB.NotAnnotated,
val classC: com.example.nested.in_another.ClassC,
val classCNested: com.example.nested.in_another.ClassCRequest.NestedRequest,
)
{
}
