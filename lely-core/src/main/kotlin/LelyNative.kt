package com.elouyi.lely

import java.io.File

public object LelyNative {

    @Deprecated(
        "this function is not working properly,use lolDirBytes Instead",
        replaceWith = ReplaceWith("String(LelyNative.lolDirBytes())"),
        level = DeprecationLevel.WARNING
    )

    /**
     * 获取 lol 游戏的安装路径，失败时返回字符串 null
     */
    @JvmStatic
    public external fun lolDir(): String

    @JvmStatic
    public external fun lolDirBytes(): ByteArray

    init {
        File("libs").mkdirs()
        val file = File("libs/cpp.dll")
        if (!file.exists()) {
            file.createNewFile()
            val inputStream = ClassLoader.getSystemResourceAsStream("cpp.dll")
            file.outputStream().use {
                it.write(inputStream.readAllBytes())
            }
        }
        System.load(file.absolutePath)
    }
}