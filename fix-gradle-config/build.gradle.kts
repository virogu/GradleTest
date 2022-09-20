plugins {
    id("java")
    id("kotlin")
    id("application")
    // https://imperceptiblethoughts.com/shadow/
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "org.example"
version = "1.0.0"

application {
    mainClass.set("org.example.MainKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}