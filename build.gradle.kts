apply("config.gradle.kts")
apply("files_opt.gradle.kts")

group = "me.virogu"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.5.31"
}

repositories {
    mavenCentral()
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_15.toString()
    }
}
tasks.compileTestKotlin {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_15.toString()
    }
}

dependencies {
}
