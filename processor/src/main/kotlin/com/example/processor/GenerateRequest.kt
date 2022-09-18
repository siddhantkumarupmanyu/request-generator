package com.example.processor

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class GenerateRequest() {

    @Target(AnnotationTarget.FIELD)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Exclude()

}
