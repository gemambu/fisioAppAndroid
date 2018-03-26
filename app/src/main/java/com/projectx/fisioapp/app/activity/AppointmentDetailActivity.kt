package com.projectx.fisioapp.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.MenuItem
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.AppointmentDetailFragment

class AppointmentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_appointment_detail)

        if (fragmentManager.findFragmentById(R.id.appointment_detail_fragment) == null) {
            val fragment = AppointmentDetailFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.appointment_detail_fragment, fragment)
                    .commit()
        }

            /*if (resources.getBoolean(R.bool.screen_sw400) != true) {

                setContentView(R.layout.activity_appointment_detail)

                if (fragmentManager.findFragmentById(R.id.appointment_detail_fragment) == null) {
                    val fragment = AppointmentDetailFragment.newInstance()
                    fragmentManager.beginTransaction()
                            .add(R.id.appointment_detail_fragment, fragment)
                            .commit()
                }
            } else {

                setContentView(R.layout.activity_calendar)
                if (fragmentManager.findFragmentById(R.id.appointments_fragment) == null) {
                    val fragment = AppointmentDetailFragment.newInstance()
                    fragmentManager.beginTransaction()
                            .add(R.id.appointments_fragment, fragment)
                            .commit()
                }
            }*/

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
}
