plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.google.devtools.ksp:symbol-processing-api:1.7.10-1.0.6")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

// https://github.com/google/ksp/issues/1040