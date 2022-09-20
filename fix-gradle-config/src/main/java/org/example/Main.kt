package org.example

import java.io.File

fun main(vararg args: String) {
    if (args.size < 2) {
        println("args ${args.toList()} incorrect")
        return
    }
    println("start with args ${args.toList()}")
    val f = File(args[0])
    if (!f.exists() || !f.isFile || !f.name.equals("gradle.xml")) {
        println("gradle.xml file path incorrect")
        return
    }
    val targetFile = File(args[1])
    if (!targetFile.exists() || !targetFile.isDirectory) {
        println("target path incorrect")
        return
    }
    val dirs = targetFile.walkBottomUp().onEnter {
        it.isDirectory && (it.name != "build" && it.name != ".gradle" && it.name != "src")
    }.filter {
        it.isDirectory && !it.listFiles { f ->
            f.isFile && (f.name == "settings.gradle.kts" || f.name == "settings.gradle")
        }.isNullOrEmpty()
    }
    var count = 0
    val force = try {
        args[2] == "1"
    } catch (e: Throwable) {
        false
    }
    dirs.forEach {
        if (force) {
            File(it.absolutePath, ".idea").deleteRecursively()
        }
        f.copyTo(File(it.absolutePath, ".idea/gradle.xml"), true)
        println(it.absolutePath)
        count++
    }
    println("$count projects gradle.xml files has fixed.")
}