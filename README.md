# Request Generator

## what/why/how
when sending api requests, the requests contains less fields than response.  
but response is what actual type is which is used in code.  
request is just a temporary type used for just requesting, with some data excluded.  
so either one have to make fields nullable just to make it request compatable.  
or write another boilerplate class with none of the required fields.  
and this is what this does. it generates that boilerplate source code, classes for you using ksp. 

just annotate your data class with `@GenerateRequest` and let this generate Request Object for that data class.

## todo
- toRequest extension function

## limitations
- only data class supported right now
