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

task("test1") {
    group = "test"
    doFirst {
        println("Test1 first")
    }
    doLast {
        println("Test1 last")
    }
}

task("test2") {
    group = "test"
    doFirst {
        println("Test2 first")
    }
    doLast {
        println("Test2 last")
    }
}

fun enable(): Boolean{
    return try {
        throw IllegalArgumentException("")
        true
    } catch (e: Throwable) {
        println("11111111111")
        false
    }
}
tasks.register("test3") {
    group = "test"
    this.setOnlyIf {
        enable()
        true
    }
    doFirst {
        println("Test3 first")
        dependsOn("test1")
    }
    doLast {
        println("Test3 last")
    }
}

