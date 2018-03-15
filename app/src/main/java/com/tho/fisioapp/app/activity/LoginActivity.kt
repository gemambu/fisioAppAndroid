package com.tho.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tho.fisioapp.R
import com.tho.fisioapp.app.router.Router
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_authenticate.setOnClickListener {
            Log.d("App", "LoginActivity --> Navigate from LoginActivity to BlankActivity")
            Router().navigateFromLoginActivitytoBlankActivity(this)
        }
    }

}
