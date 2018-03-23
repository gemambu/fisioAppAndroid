package com.projectx.fisioapp.app.router

import android.content.Intent
import com.projectx.fisioapp.app.activity.*
import java.util.*


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

    fun navigateFromCalendarActivityToAppointmentDetailActivity(main: CalendarActivity){
        main.startActivity(Intent(main.baseContext, AppointmentDetailActivity::class.java))
    }

}
