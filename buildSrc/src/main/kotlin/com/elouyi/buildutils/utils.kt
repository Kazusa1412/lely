package com.elouyi.buildutils

import org.gradle.api.Project

val Project.localProperties: LocalProperties
    get() = LocalProperties.getLocalProperties(this)

