package com.elouyi.lely

import com.elouyi.lely.LelyNative.lolDirBytes


public fun main() {
    println("您的 LOL 安装地址是")
    val s = String(lolDirBytes())
    println(s)

}