
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"
}

group = "com.vengeful"
version = "0.0.1"

application {
    mainClass.set("com.vengeful.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    val room_version = "2.7.0-alpha01"
    implementation("androidx.room:androidx.room.gradle.plugin:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    //implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.sqlite:sqlite:2.5.0-alpha01")
    implementation("androidx.sqlite:sqlite-bundled:2.5.0-alpha01")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0-RC")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-cio-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
