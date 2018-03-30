package com.projectx.fisioapp.app.activity

import android.os.Bundle
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserInteractor
import com.projectx.fisioapp.domain.interactor.users.registeruser.RegisterUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.registeruser.RegisterUserInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : ParentActivity() {


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

        setFakeDataInForm()

    }

    fun setFakeDataInForm() {
        etName.setText( "Only needed to register")
        etEmail.setText( "example6@kk.com")
        etPass.setText("temp1234")
    }

    private fun AuthenticateUser() {
        val authenticateUser: AuthenticateUserInteractor = AuthenticateUserIntImpl(this)
        try {
            authenticateUser.execute(
                    etEmail.text.toString(),
                    etPass.text.toString(),
                    success = object : SuccessCompletion<String> {
                        override fun successCompletion(e: String) {
                            ToastIt(baseContext, "Your token is: $e")
                            token = e
                            checkToken()
                        }
                    }, error = object : ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    ToastIt(baseContext, "$errorMessage")
                }
            })
            checkToken()
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }

    private fun RegisterUser() {
        val registerUser: RegisterUserInteractor = RegisterUserIntImpl(this)
        try {
            registerUser.execute(
                    etName.text.toString(),
                    etEmail.text.toString(),
                    etPass.text.toString(),
                    success = object : SuccessCompletion<Boolean> {
                        override fun successCompletion(e: Boolean) {
                            ToastIt(baseContext, "Transaction is: $e")
                            checkToken()
                        }
                    }, error = object : ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    ToastIt(baseContext, "$errorMessage")
                }
            })
            checkToken()
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }


}
