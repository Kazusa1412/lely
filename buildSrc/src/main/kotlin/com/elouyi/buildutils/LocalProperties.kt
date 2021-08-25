package com.elouyi.buildutils

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*
import kotlin.reflect.KProperty


class LocalProperties private constructor(delegate: Project) {

    // 简单单例
    companion object {
        var localProperties: LocalProperties? = null

        fun getLocalProperties(project: Project): LocalProperties {
            return localProperties ?: LocalProperties(project)
        }
    }

    private val localProperties = Properties()

    init {
        localProperties.load(FileInputStream(delegate.rootProject.file("local.properties")))
    }

    operator fun getValue(clazz: Any?,prop: KProperty<*>): String? {
        val propName = prop.name
        return localProperties[propName].run {
            if (this !== null) toString()
            else null
        }
    }

    operator fun get(key: String): Any? = localProperties[key]
}

