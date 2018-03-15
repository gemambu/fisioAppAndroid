package com.projectx.fisioapp.repository.cache

import android.content.Context
import com.projectx.fisioapp.repository.preferencehelper.PreferenceHelper
import com.projectx.fisioapp.repository.preferencehelper.PreferenceHelper.get
import java.lang.ref.WeakReference


class CacheIntImpl(val context: Context): CacheInteractor{
    private val weakContext = WeakReference<Context>(context)

    override fun getToken(success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = getToken()
            success(token)
        } else {
            error("No token")
        }
    }

    fun getToken(): String {
        // Get token from SharedPreferences
        var prefs = PreferenceHelper.defaultPrefs(weakContext.get() !!)
        val token: String = prefs[PreferenceHelper.KEY_TOKEN] ?: ""
        return token
    }

}