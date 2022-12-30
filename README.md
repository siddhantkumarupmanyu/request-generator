# Request Generator

[![ci](https://github.com/siddhantkumarupmanyu/request-generator/actions/workflows/ci.yml/badge.svg)](https://github.com/siddhantkumarupmanyu/request-generator/actions/workflows/ci.yml)

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

## others
new features for request generator. <br/>

first generate to extension functions. on both. <br/>
if the data object needs more info you need to provide that, pass in as function parameter. <br/>

second retain other annotations. <br/>
like @JvmRecord. <br/>

third, support for more than just request. <br/>
like i want response too. <br/>
so request and well as response from model. <br/>
the challenge here is having to distinguish different excludes. <br/>

if you go with two. fine... <br/>
i think only request response. it should be fine. why do anyone wants to generate more than these two classes. <br/>
if so its misusing. <br/>

that would be a challenge. but i think thats just wrong way to do thing. <br/>

