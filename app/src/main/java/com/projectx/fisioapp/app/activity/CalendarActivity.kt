package com.projectx.fisioapp.app.activity

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.AppointmentDetailFragment
import com.projectx.fisioapp.app.fragment.AppointmentsListFragment
import com.projectx.fisioapp.app.fragment.CalendarFragment
import com.projectx.fisioapp.app.router.Router
import java.util.*

class CalendarActivity : AppCompatActivity(), AppointmentsListFragment.OnSelectedAppointmentListener {


    lateinit var calendarFragment: CalendarFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        calendarFragment = supportFragmentManager.findFragmentById(R.id.calendar_fragment) as CalendarFragment

        if (fragmentManager.findFragmentById(R.id.appointments_fragment) == null){
            val fragment = AppointmentsListFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.appointments_fragment, fragment)
                    .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onSelectedAppointment(date: Date) {
        Router().navigateFromCalendarActivityToAppointmentDetailActivity(this)

        /*val fragment = AppointmentDetailFragment.newInstance()
        fragmentManager.beginTransaction()
                .replace(R.id.appointments_fragment, fragment)
                .commit()*/

        /*if(resources.getBoolean(R.bool.screen_not_sw600) == false){
            Router().navigateFromCalendarActivityToAppointmentDetailActivity(this)
        } else if(resources.getBoolean(R.bool.screen_is_sw600) == false){
            val fragment = AppointmentDetailFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.appointments_fragment, fragment)
                    .commit()
        }*/
    }
}
