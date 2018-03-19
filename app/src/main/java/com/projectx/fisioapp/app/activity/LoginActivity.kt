package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.projectx.fisioapp.R

import com.projectx.fisioapp.app.settingsmanager.SettingsManager
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val settingsManager = SettingsManager()
    var token: String
        get() {
            val token = settingsManager.getCustomSharedPreference(
                    this,
                    settingsManager.FILE_USER_PREFERENCES,
                    settingsManager.KEY_TOKEN
            ) as String?

            return token ?: ""
        }
    set(value) {
        settingsManager.setCustomSharedPreference(this, settingsManager.FILE_USER_PREFERENCES, settingsManager.KEY_TOKEN, value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnAuthenticate.setOnClickListener {
            ToastIt(this, "Trying to authenticate")
            AuthenticateUser()
        }

        btnRegister.setOnClickListener {
            ToastIt(this, "Trying to register")
            RegisterUser()
        }

    }

    fun AuthenticateUser() {
        val authenticateUser: AuthenticateUserInteractor = AuthenticateUserIntImpl(this)
        try {
            authenticateUser.execute(
                    "fisio@invalid.com",
                    password = "12345678",
                    success = object : SuccessCompletion<String> {
                        override fun successCompletion(e: String) {
                            Log.d("App", "authenticateUser ok: $e")
                            ToastIt(baseContext, "Your token is: $e")
                            token = e
                            checkToken()
                        }
                    }, error = object : ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    Log.d("App", "authenticateUser error: $errorMessage")
                    ToastIt(baseContext, "$errorMessage")
                }
            })
            checkToken()
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }

    fun RegisterUser() {
        ToastIt(this, "Not yet implemented")
    }

    fun checkToken() {
        if (token.length != 0) finish()
    }

}
