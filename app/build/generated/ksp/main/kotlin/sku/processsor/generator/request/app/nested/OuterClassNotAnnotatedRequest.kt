package sku.processsor.generator.request.app.nested

data class OuterClassNotAnnotatedRequest (
val field: kotlin.Int,
)
{

data class NestedAnnotatedRequest (
val field: kotlin.Int,
)
{
}
}
