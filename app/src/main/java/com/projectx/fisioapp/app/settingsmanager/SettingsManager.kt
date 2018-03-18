package com.projectx.fisioapp.app.settingsmanager

import android.content.Context
import android.content.SharedPreferences
import com.projectx.fisioapp.app.sharedpreferenceswrapper.SharedPreferencesWrapper


interface SettingsManagerInteractor {

    interface SharedPreferencesWrapperInteractor {
        fun defaultSharedPrefs(context: Context): SharedPreferences
        fun customSharedPrefs(context: Context, filename: String): SharedPreferences
        fun getCustomPreference(context: Context, filename: String, key: String): Any?
        fun getCustomPreferences(context: Context, filename: String): SharedPreferences?
        fun putCustomPreference(context: Context, filename: String, key: String, value: Any?)
        fun clearCustomPreferences(context: Context, filename: String)
        fun deleteCustomPreferences(context: Context, filename: String, key: String)
    }

}

class SettingsManager {
    var sharedPreferencesWrapper: SharedPreferencesWrapper

    init {
        sharedPreferencesWrapper = SharedPreferencesWrapper
    }

    enum class Platform {
        ANDROID,
        IOS,
        UNKNOWN,
        WEB
    }

    // Fields
    val KEY_PLATFORM = "PLATFORM"
    val KEY_TOKEN = "TOKEN"

    // Files
    val FILE_USER_PREFERENCES = "USER_PREFERENCES"

    val ALL_KEYS_PREFERENCES = arrayOf(
            KEY_PLATFORM,
            KEY_TOKEN
    )

    val ALL_FILES_PREFERENCES = arrayOf(
            FILE_USER_PREFERENCES
    )

    fun defaultSharedPrefs(context: Context): SharedPreferences =
            sharedPreferencesWrapper.defaultSharedPrefs(context)

    fun customSharedPrefs(context: Context, filename: String): SharedPreferences =
            sharedPreferencesWrapper.customSharedPrefs(context, filename)

    inline fun <reified T : Any> getCustomPreference(context: Context, filename: String, key: String): T? =
            sharedPreferencesWrapper.getCustomPreference(context, filename, key) as T?

    fun getCustomPreferences(context: Context, filename: String): SharedPreferences? =
            sharedPreferencesWrapper.getCustomPreferences(context, filename)

    fun putCustomPreference(context: Context, filename: String, key: String, value: Any?) {
        sharedPreferencesWrapper.putCustomPreference(context, filename, key, value)
    }

    fun clearCustomPreferences(context: Context, filename: String) {
        sharedPreferencesWrapper.clearCustomPreferences(context, filename)
    }

    fun deleteCustomPreferences(context: Context, filename: String, key: String) {
        sharedPreferencesWrapper.deleteCustomPreferences(context, filename, key)
    }

}
