package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projectx.fisioapp.R

import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.settingsmanager.SettingsManager
import com.projectx.fisioapp.app.utils.ToastIt


class AppointmentsActivity : AppCompatActivity() {

    val settingsManager = SettingsManager()
    var token: String
        get() {
            /**/
            settingsManager.setCustomSharedPreference(this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_TOKEN,
                    "")
                    /**/
            val token = settingsManager.getCustomSharedPreference<String>(
                    this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_TOKEN
            ) as String?

            return token ?: ""
        }
        set(value) {
            // Always blank to revoke token
            settingsManager.setCustomSharedPreference(this, settingsManager.FILE_USER_PREFERENCES, settingsManager.KEY_TOKEN, "")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        checkToken()

    }

    fun checkToken() {
        if (token.length == 0) Router().navigateFromAppointmentsActivitytoLoginActivity(this)
    }

}
