import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.shtanko"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}