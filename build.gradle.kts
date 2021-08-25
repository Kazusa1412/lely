group = "com.elouyi"
version = "0.0.1"

buildscript {

    repositories {
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
    }
}
