package com.mrtdev.quoteslove.storage

import android.content.Context
import android.content.SharedPreferences

abstract class BaseStorage(context: Context, prefsName: String) {
    protected val prefs: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    protected inline fun <reified T> get(key: String): T? =
        if (prefs.contains(key)) {
            when (T::class) {
                Boolean::class -> prefs.getBoolean(key, false) as T?
                String::class -> prefs.getString(key, null) as T?
                Float::class -> prefs.getFloat(key, 0f) as T?
                Int::class -> prefs.getInt(key, 0) as T?
                Long::class -> prefs.getLong(key, 0L) as T?
                else -> null
            }
        } else {
            null
        }

    protected fun <T> set(key: String, value: T?) {
        prefs.edit().apply {
            when (value) {
                null -> remove(key)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
            }
        }.apply()
    }

}
