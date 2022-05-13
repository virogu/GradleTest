package common

import org.gradle.kotlin.dsl.application

plugins {
    // Apply the common convention plugin for shared build configuration between library and application projects.
    id("common.kotlin-common-conventions")

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}
