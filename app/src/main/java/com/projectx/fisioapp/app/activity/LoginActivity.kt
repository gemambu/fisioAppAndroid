package com.projectx.fisioapp.app.activity

import android.os.Bundle
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.authenticateuser.AuthenticateUserInteractor
import com.projectx.fisioapp.domain.interactor.users.registeruser.RegisterUserIntImpl
import com.projectx.fisioapp.domain.interactor.users.registeruser.RegisterUserInteractor
import com.projectx.fisioapp.domain.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : ParentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnAuthenticate.setOnClickListener {
            authenticateUser()
        }

        btnRegister.setOnClickListener {
            registerUser()
        }

        setFakeDataInForm()

    }

    fun setFakeDataInForm() {
        etName.setText( "Only needed to register")
        etEmail.setText( "fisio@invalid.com")
        etPass.setText("12345678")
    }

    private fun authenticateUser() {
        val authenticateUser: AuthenticateUserInteractor = AuthenticateUserIntImpl(this)
        try {
            authenticateUser.execute(
                    etEmail.text.toString(),
                    etPass.text.toString(),
                    success = { user: User, tkn: String ->
                        token = tkn
                        uId = user.id
                        ToastIt(baseContext, "TK: $token")
                        ToastIt(baseContext, "uId: $uId")
                        if (checkToken()) finish()
                    }, error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {
                            ToastIt(baseContext, errorMessage)
                        }
                    })
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }

    private fun registerUser() {
        val registerUser: RegisterUserInteractor = RegisterUserIntImpl(this)
        try {
            registerUser.execute(
                    etName.text.toString(),
                    etEmail.text.toString(),
                    etPass.text.toString(),
                    success = { ok: Boolean, msg: String ->
                        if (ok) {
                            ToastIt(baseContext, "User created")
                            ToastIt(baseContext, "You can press 'Login' to start")
                        } else {
                            ToastIt(baseContext, "Success/error: Try again")
                        }
                    }, error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {
                            ToastIt(baseContext, errorMessage)
                        }
                    })
        } catch (e: Exception) {
            ToastIt(this, "Error: " + e.localizedMessage )
        }
    }


}
