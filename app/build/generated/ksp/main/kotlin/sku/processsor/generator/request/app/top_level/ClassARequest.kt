package sku.processsor.generator.request.app.top_level

data class ClassARequest (
val field: kotlin.Int,
val classB: com.example.top_level.ClassBRequest,
val classC: com.example.top_level.ClassC,
)
{
}
