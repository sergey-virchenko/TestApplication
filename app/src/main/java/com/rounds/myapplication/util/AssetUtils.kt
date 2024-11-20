package com.rounds.myapplication.util

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.BufferedReader
import java.io.InputStreamReader

fun readAssetFileToString(context: Context, fileName: String): String {
    val content = StringBuilder()
    try {
        context.assets.open(fileName).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.forEachLine { line ->
                    content.append(line).append("\n")
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return content.toString()
}

inline fun <reified T> Moshi.parseList(jsonString: String): List<T>? {
    return adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java)).fromJson(
        jsonString
    )
}
