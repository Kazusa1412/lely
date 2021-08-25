
plugins {
    `cpp-library`
}


library {
    dependencies {
    }
    publicHeaders {
        from(File("P:\\dev\\env\\jdk\\11\\include"))

    }

    privateHeaders {
        from(File("P:\\dev\\env\\jdk\\11\\include\\win32"))
        from(File("P:\\dev\\env\\jdk\\11\\include\\win32\\bridge"))
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