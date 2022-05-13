
plugins {
    id("common.kotlin-application-conventions")
    id("files.files-opt")
    id("configs.config")
}

dependencies {
    implementation("org.apache.commons:commons-text")
}

application {
    // Define the main class for the application.
    mainClass.set("com.app.AppKt")
}
