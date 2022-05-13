import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply {
    from("config.gradle.kts")
    from("file_opt.gradle.kts")
}

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}