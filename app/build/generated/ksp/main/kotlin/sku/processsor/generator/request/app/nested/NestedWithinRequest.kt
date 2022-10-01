package sku.processsor.generator.request.app.nested

data class NestedWithinRequest (
    val field: kotlin.Int,
    val nested1: sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested1Request,
    val nested2: _root_ide_package_.sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested2Request,
)
{

data class Nested1Request (
    val nested11: _root_ide_package_.sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested1Request.Nested11Request,
)
{

data class Nested11Request (
val field11: kotlin.Int,
)
{
}
}

data class Nested2Request (
    val nested1: _root_ide_package_.sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested1Request,
    val nested11: _root_ide_package_.sku.processsor.generator.request.app.nested.NestedWithinRequest.Nested1Request.Nested11Request,
    val field2: kotlin.Int,
)
{
}
}
