package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projectx.fisioapp.R

import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.settingsmanager.SettingsManager
import com.projectx.fisioapp.app.utils.ToastIt


class AppointmentsActivity : AppCompatActivity() {

    private val settingsManager = SettingsManager()
    private var token: String
        get() {
            val token = settingsManager.getCustomSharedPreference<String>(
                    this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_TOKEN
            )

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

    private fun checkToken() {
        if (token.length == 0) Router().navigateFromAppointmentsActivitytoLoginActivity(this)
    }

}
