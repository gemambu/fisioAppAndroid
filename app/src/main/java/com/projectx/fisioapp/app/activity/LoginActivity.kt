package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.projectx.fisioapp.R

import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnAuthenticate.setOnClickListener {
            ToastIt(this, 'd', "Trying to authenticate")
            AuthenticateUser()
        }

        btnRegister.setOnClickListener {
            ToastIt(this, 'd', "Trying to register")
            RegistaerUser()
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
                            ToastIt(baseContext, 'd', "Your token is: $e")
                        }
                    }, error = object : ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    Log.d("App", "authenticateUser error: $errorMessage")
                    ToastIt(baseContext, 'd', "$errorMessage")
                }
            })
            Router().navigateFromLoginActivitytoBlankActivity(this)
        } catch (e: Exception) {
            ToastIt(this, 'd', "Error: " + e.localizedMessage )
        }
    }

    fun RegistaerUser() {
        ToastIt(this, 'd', "Not yet implemented")
    }

}
