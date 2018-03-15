package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.projectx.fisioapp.R

import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserIntFakeImpl
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_authenticate.setOnClickListener {
            finish()
        }

    }

    fun AuthenticateUser() {
        val authenticateUser: AuthenticateUserInteractor = AuthenticateUserIntFakeImpl()
        authenticateUser.execute(
                "fisio@invalid.com",
                password="12345678",
                success =  object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        Log.d("App", "authenticateUser ok: $e")
                        Toast.makeText(baseContext, "Your token is: $e", Toast.LENGTH_LONG).show()
                    }
                }, error =  object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("App", "authenticateUser error: $errorMessage")
                        Toast.makeText(baseContext, "$errorMessage", Toast.LENGTH_LONG).show()
                    }
                })
        Router().navigateFromLoginActivitytoBlankActivity(this)
    }

}
