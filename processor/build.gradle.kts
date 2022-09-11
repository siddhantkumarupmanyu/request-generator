import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        apiVersion = "1.7"
        languageVersion = "1.7"
        jvmTarget = "17"
    }
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}