package com.tho.fisioapp.app.router

import android.content.Intent
import com.tho.fisioapp.app.activity.BlankActivity
import com.tho.fisioapp.app.activity.LoginActivity


class Router {

    fun navigateFromLoginActivitytoBlankActivity (main: LoginActivity) {
        main.startActivity(Intent(main, BlankActivity::class.java))
    }

}
