import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

val gitCommitCount: Int = with(ByteArrayOutputStream()) {
    use { os ->
        exec {
            executable = "git"
            args = listOf("rev-list", "--all", "--count")
            standardOutput = os
        }
        val revision = os.toString().trim()
        return@with revision.toInt()
    }
}
val buildFormatDate: String = with(SimpleDateFormat("yyMMdd")) {
    format(Date())
}
val gitCommitShortid: String = with(ByteArrayOutputStream()) {
    use { os ->
        exec {
            executable = "git"
            args = listOf("rev-parse", "--short", "HEAD")
            standardOutput = os
        }
        return@with os.toString().trim()
    }
}
val myPackageVersion: String = "3.21.${gitCommitCount}"
val buildVersion: String = "3.${buildFormatDate}_${gitCommitShortid}"

val kotlinVersion: String by project
val myPackageName: String by project
val myMenuGroup: String by project
val myPackageVendor: String by project
val winUpgradeUuid: String by project

project.extra["gitCommitCount"] = gitCommitCount
project.extra["buildFormatDate"] = buildFormatDate
project.extra["gitCommitShortid"] = gitCommitShortid
project.extra["myPackageVersion"] = myPackageVersion
project.extra["buildVersion"] = buildVersion

tasks.create("packageMsiAndRename") {
    group = "compose desktop"
    //dependsOn("packageMsi")
    doLast {
        println("do rename task")
        project.rootDir.resolve("out/packages/main/msi").listFiles()?.filter {
            it.name.endsWith(".msi")
        }?.forEach {
            val newName = "MVSTool-3.${buildFormatDate}(${gitCommitCount})_${gitCommitShortid}.msi"
            println("rename [${it.name}] to [$newName]")
            it.renameTo(File(it.parentFile, newName))
        }
    }
}

task("zipPackageFiles", Zip::class) {
    rootProject.rootDir.resolve("out/zip").apply {
        println("clear path:[${this.path}]")
        this.deleteRecursively()
    }
    group = "compose desktop"
    from("C:\\Program Files\\MVSTool") {
        //include {
        //    println("found file [${it.path}]")
        //    true
        //}
    }
    archiveBaseName.set("MVSTool")
    archiveAppendix.set("3.${buildFormatDate}(${gitCommitCount})")
    archiveVersion.set(gitCommitShortid)
    archiveExtension.set("zip")
    destinationDirectory.set(rootProject.rootDir.resolve("out/zip"))
    doLast {
        val zipFile = archiveFile.get().asFile
        val size = DecimalFormat(".##").format(zipFile.length() / (1024 * 1024f))
        println("zip file [${zipFile.path}] success, size: ${size}MB")
    }
}