import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
    idea
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":processor"))
    ksp(project(":processor"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

application {
    mainClass.set("com.example.AppKt")
}

// on why hardcoding
// https://github.com/gradle/gradle/issues/9331#issuecomment-1242992483
// plugin authors evaluating task lazily, and they have to cause source data is available lazily to them,
// it's how gradle works...

// anyway can use a variable. and configure ksp and here with that

idea {
    module {
        val sourceDestination = file("build/generated/ksp/main/kotlin")
        val testDestination = file("build/generated/ksp/test/kotlin")
        sourceDirs = sourceDirs + sourceDestination
        testSourceDirs = sourceDirs + testDestination
        generatedSourceDirs = generatedSourceDirs + sourceDestination + testDestination
    }
}

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
