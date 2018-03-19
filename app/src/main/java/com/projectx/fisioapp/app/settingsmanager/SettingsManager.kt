package com.projectx.fisioapp.app.settingsmanager

import android.content.Context
import android.content.SharedPreferences
import com.projectx.fisioapp.app.sharedpreferenceswrapper.SharedPreferencesWrapper
import com.projectx.fisioapp.app.sharedpreferenceswrapper.SharedPreferencesWrapper.get


interface SettingsManagerInteractor {

    interface SharedPreferencesWrapperInteractor {
        fun defaultSharedPreferences(context: Context): SharedPreferences
        fun customSharedPreferences(context: Context, filename: String): SharedPreferences
        fun getCustomSharedPreference(context: Context, filename: String, key: String): Any?
        fun setCustomSharedPreference(context: Context, filename: String, key: String, value: Any?)
        fun deleteCustomSharedPreference(context: Context, filename: String, key: String)
        fun clearCustomFileSharedPreferences(context: Context, filename: String)
    }

}

class SettingsManager {
    var sharedPreferencesWrapper: SharedPreferencesWrapper

    init {
        sharedPreferencesWrapper = SharedPreferencesWrapper
    }

    // Fields
    val KEY_TOKEN = "TOKEN"

    // Files
    val FILE_USER_PREFERENCES = "USER_PREFERENCES"

    val ALL_KEYS_PREFERENCES = arrayOf(
            KEY_TOKEN
    )

    val ALL_FILES_PREFERENCES = arrayOf(
            FILE_USER_PREFERENCES
    )

    fun defaultSharedPreferences(context: Context): SharedPreferences =
            sharedPreferencesWrapper.defaultSharedPreferences(context)

    fun customSharedPreferences(context: Context, filename: String): SharedPreferences =
            sharedPreferencesWrapper.customSharedPreferences(context, filename)

    fun getCustomSharedPreference(context: Context, filename: String, key: String) =
            sharedPreferencesWrapper.getCustomSharedPreference(context, filename, key)

    fun setCustomSharedPreference(context: Context, filename: String, key: String, value: Any?) {
        sharedPreferencesWrapper.setCustomSharedPreference(context, filename, key, value)
    }

    fun deleteCustomSharedPreference(context: Context, filename: String, key: String) {
        sharedPreferencesWrapper.deleteCustomSharedPreference(context, filename, key)
    }

    fun clearCustomFileSharedPreferences(context: Context, filename: String) {
        sharedPreferencesWrapper.clearCustomFileSharedPreferences(context, filename)
    }

}
