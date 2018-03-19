package com.projectx.fisioapp.repository.cache

import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference


class CacheIntFakeImpl(val context: Context): CacheInteractor{
    private val weakContext = WeakReference<Context>(context)

    override fun getToken(success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = getTokenFromSharedPreferences()
            success(token)
        } else {
            error("Error while getting token")
            Log.d("App", "Error")
        }
    }

    fun getTokenFromSharedPreferences(): String {

        // Random token
        val token = "token12345"
        return token

    }

}