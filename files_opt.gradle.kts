import kotlin.system.measureTimeMillis

task("clearAllBuildFiles") {
    group = "filesOperate"
    doFirst {
        File("E:\\").walk().filter {
            it.isDirectory && it.name == "build" && it.parentFile.name == "app"
        }.forEach {
            println("clear build dir [${it.absolutePath}]")
            it.deleteRecursively()
        }
    }
}