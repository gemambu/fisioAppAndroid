package com.projectx.fisioapp.app.router

import android.content.Intent
import com.projectx.fisioapp.app.activity.AppointmentsActivity
import com.projectx.fisioapp.app.activity.BlankActivity
import com.projectx.fisioapp.app.activity.LoginActivity


class Router {

    fun navigateFromAppointmentsActivitytoLoginActivity (main: AppointmentsActivity) {
        main.startActivity(Intent(main, LoginActivity::class.java))
    }

    fun navigateFromLoginActivitytoAppointmentsActivity (main: LoginActivity) {
        main.startActivity(Intent(main, AppointmentsActivity::class.java))
    }

    fun navigateFromLoginActivitytoBlankActivity (main: LoginActivity) {
        main.startActivity(Intent(main, BlankActivity::class.java))
    }

}
