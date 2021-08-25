
plugins {
    kotlin("jvm")
}

kotlin {
    explicitApi()


}

dependencies {
    // implementation(project(":cpp"))
}

// for jar test
tasks.register<Jar>("lelyJar") {
    group = "elouyi"
    // archive 信息
    archiveClassifier.set(Versions.lely)

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
    exclude(
        "META-INF/*.SF",
        "META-INF/*.DSA",
        //"META-INF/*.RSA",
        //"META-INF/*.kotlin_module",
        "META-INF/*.md"
        //"**/*.kotlin_metadata",
        //"**/*.kotlin_builtins",
        //"**/*.kotlin_module"
    )
    manifest {
        attributes(
            // 主类
            "Main-Class" to "com.elouyi.lely.JniTestKt",
            "lely-Version" to Versions.lely
        )
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        targetCompatibility = "1.8"
    }
}