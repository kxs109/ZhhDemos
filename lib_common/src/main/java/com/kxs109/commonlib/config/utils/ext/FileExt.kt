package com.kxs109.commonlib.config.utils.ext

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type

/**
 * 从jsonFile解析object
 */
fun <T> fromJsonFile(obj: Gson, context: Context, fileName: String, typeOfT: Type): T {
    val stringBuilder = StringBuilder()
    try {
        val assetManager = context.assets
        val bf = BufferedReader(
            InputStreamReader(
                assetManager.open(fileName)
            )
        )
        var line: String?
        while (bf.readLine().run {
                line = this
                this != null
            }) {
            stringBuilder.append(line)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return obj.fromJson(stringBuilder.toString(), typeOfT)
}
