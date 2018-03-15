package com.tho.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Toast
import com.tho.fisioapp.R
import com.tho.fisioapp.app.router.Router
import com.tho.fisioapp.domain.interactor.ErrorCompletion
import com.tho.fisioapp.domain.interactor.SuccessCompletion
import com.tho.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserIntFakeImpl
import com.tho.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_authenticate.setOnClickListener {
            Log.d("App", "LoginActivity --> Navigate from LoginActivity to BlankActivity")
            AuthenticateUser()
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
                        Toast.makeText(baseContext, "Your token is: $e", Toast.LENGTH_SHORT).show()
                    }
                }, error =  object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("App", "authenticateUser error: $errorMessage")
                        Toast.makeText(baseContext, "$errorMessage", Toast.LENGTH_SHORT).show()
                    }
                })
        Router().navigateFromLoginActivitytoBlankActivity(this)
    }

}
