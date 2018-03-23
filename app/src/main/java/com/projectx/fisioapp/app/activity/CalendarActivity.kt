package com.projectx.fisioapp.app.activity

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.AppointmentsListFragment
import com.projectx.fisioapp.app.fragment.CalendarFragment
import com.projectx.fisioapp.app.router.Router
import java.util.*

class CalendarActivity : AppCompatActivity(), AppointmentsListFragment.OnSelectedAppointmentListener {


    lateinit var calendarFragment: CalendarFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarFragment = supportFragmentManager.findFragmentById(R.id.calendar_fragment) as CalendarFragment

        if (fragmentManager.findFragmentById(R.id.appointments_fragment) == null){
            val fragment = AppointmentsListFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.appointments_fragment, fragment)
                    .commit()
        }

    }


    override fun onSelectedAppointment(date: Date) {
        Router().navigateFromCalendarActivityToAppointmentDetailActivity(this)
    }
}
