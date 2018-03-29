package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import com.projectx.fisioapp.app.settingsmanager.SettingsManager

open class ParentActivity : AppCompatActivity() {

    private val settingsManager = SettingsManager()
    protected var token: String
        get() {
            val token = settingsManager.getCustomSharedPreference<String>(
                    this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_TOKEN
            )

            return token ?: ""
        }
        set(value) {
            settingsManager.setCustomSharedPreference(this, settingsManager.FILE_USER_PREFERENCES, settingsManager.KEY_TOKEN, value)
        }

    protected fun checkToken(): Boolean {
        return token.length !== 0
    }
}
