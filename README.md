# Request Generator

## what/why/how
when sending api requests, the requests contains less fields than response.  
but response is what actually used throughtout.  
request type can thought of as a temporary type used for requesting.  
its response with some fields removed.  
so either one have to make fields nullable. 
or write another boilerplate class with none of the required fields.  
and this is what it does. it generates that boilerplate source code/classes without those required fields.

just annotate your data class with `@GenerateRequest` and let this generate Request Type for that data class.  
to exclude a field annotate the field with `@GenerateRequest.Exclude`

## todo
- toRequest extension function

## limitations
- only data class supported right now
