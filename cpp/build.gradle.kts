import com.elouyi.buildutils.localProperties

plugins {
    `cpp-library`
}


library {
    dependencies {
    }

    // 在项目根目录的 local.properties 中写入javaHome,与 bin 目录同级
    // 例如 javaHome=C\:/jdk/11
    val javaHome by localProperties
    if (javaHome === null) {
        System.err.println("需要在 local.properties 中配置 javaHome")
    }
    publicHeaders {
        from(File("$javaHome/include"))
    }

    privateHeaders {
        from(File("$javaHome/include/win32"))
        from(File("$javaHome/include/win32/bridge"))
    }
}

tasks.register<Delete>("clearLib") {
    group = "elouyi"
    delete("../libs")
    delete("../lely-core/src/main/resources")
}

tasks.register("buildDll") {
    group = "elouyi"
    // dependsOn("assembleRelease")
    dependsOn("assemble")
    dependsOn("clearLib")
    doLast {
        // release 的会乱码?
        val cppFile = file("build/lib/main/debug/cpp.dll")
        if (!cppFile.exists()) throw kotlin.IllegalStateException("cant find dll file at ${cppFile.absolutePath}")
        val parentCppFile = parent!!.file("libs/cpp.dll")
        val lelyCppResource = parent!!.file("lely-core/src/main/resources/cpp.dll")
        cppFile.copyTo(parentCppFile,true)
        cppFile.copyTo(lelyCppResource,true)
    }

}