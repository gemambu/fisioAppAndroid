package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.projectx.fisioapp.R
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.users.gettoken.GetTokenIntImpl
import com.projectx.fisioapp.domain.interactor.users.gettoken.GetTokenInteractor


class AppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        getToken()
    }

    fun getToken() {

        val getToken: GetTokenInteractor = GetTokenIntImpl(this)
        getToken.execute(
                object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        if (e.length == 0) {
                            Toast.makeText(baseContext, "Try to authenticate again.\nThere is no token saved.", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(baseContext, "Your default token is: $e", Toast.LENGTH_LONG).show()
                        }
                    }
                }, object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Toast.makeText(baseContext, "There was an error: $errorMessage", Toast.LENGTH_LONG).show()
                        //Router().navigateFromAppointmentsActivitytoLoginActivity(this)
                    }
                }
        )

    }

}
