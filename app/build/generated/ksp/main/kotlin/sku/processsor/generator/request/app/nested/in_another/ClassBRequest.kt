package sku.processsor.generator.request.app.nested.in_another

data class ClassBRequest (
val field: kotlin.Int,
)
{

data class NestedRequest (
val fieldNested: kotlin.Int,
)
{
}
}
