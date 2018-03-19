package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.settingsmanager.SettingsManager
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.users.gettoken.GetTokenIntImpl
import com.projectx.fisioapp.domain.interactor.users.gettoken.GetTokenInteractor


class AppointmentsActivity : AppCompatActivity() {

    val settingsManager = SettingsManager()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        settingsManager.setCustomSharedPreference(
                this,
                settingsManager.FILE_USER_PREFERENCES,
                settingsManager.KEY_TOKEN,
                "12345token"
        )
        settingsManager.setCustomSharedPreference(
                this,
                settingsManager.FILE_USER_PREFERENCES,
                "INTE",
                1234.toInt()
        )
        settingsManager.setCustomSharedPreference(
                this,
                settingsManager.FILE_USER_PREFERENCES,
            "FLO",
            1234.30.toFloat()
        )
        settingsManager.customSharedPreferences(this, settingsManager.FILE_USER_PREFERENCES)

        getToken()

    }

    fun getToken(): String {

        // If token return token else goto Loginactivity
        val token = settingsManager.getCustomSharedPreference(
                this,
                settingsManager.FILE_USER_PREFERENCES,
                settingsManager.KEY_TOKEN
        ) as String?

        return token!!



        /*
        val getToken: GetTokenInteractor = GetTokenIntImpl(this)
        getToken.execute(
                object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        if (e.length == 0) {
                            Toast.makeText(baseContext, "Try to authenticate again.\nThere is no token saved.", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(baseContext, "Your default token is: $e", Toast.LENGTH_LONG).show()
                            Log.d("App", "Your default token is: $e")
                        }
                    }
                }, object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Toast.makeText(baseContext, "There was an error: $errorMessage", Toast.LENGTH_LONG).show()
                        //Router().navigateFromAppointmentsActivitytoLoginActivity(this)
                    }
                }
        )
        */

    }

}
