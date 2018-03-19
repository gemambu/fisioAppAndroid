package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.settingsmanager.SettingsManager


class AppointmentsActivity : AppCompatActivity() {

    val settingsManager = SettingsManager()
    var token: String = ""
        get() {
            //settingsManager.setCustomSharedPreference(this, settingsManager.FILE_USER_PREFERENCES, settingsManager.KEY_TOKEN, "12345token")
            val token = settingsManager.getCustomSharedPreference(
                    this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_TOKEN
            ) as String?

            return token ?: ""
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        getTokenOrRedirectToLogin()

    }

    fun getTokenOrRedirectToLogin() {
        if (token.length == 0) Router().navigateFromAppointmentsActivitytoLoginActivity(this)
    }

}
