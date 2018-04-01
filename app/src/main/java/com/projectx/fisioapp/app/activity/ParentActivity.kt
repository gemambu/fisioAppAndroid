package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import com.projectx.fisioapp.app.settingsmanager.SettingsManager

open class ParentActivity : AppCompatActivity() {

    private val settingsManager = SettingsManager()

    /******** users ********/

    // Token
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

    // UId
    protected var uId: String
        get() {
            val uId = settingsManager.getCustomSharedPreference<String>(
                    this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_UID
            )

            return uId ?: ""
        }
        set(value) {
            settingsManager.setCustomSharedPreference(this, settingsManager.FILE_USER_PREFERENCES, settingsManager.KEY_UID, value)
        }

    protected fun checkUId(): Boolean {
        return uId.length !== 0
    }

}
