package com.projectx.fisioapp.app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.MenuItem
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.AppointmentDetailFragment
import com.projectx.fisioapp.domain.model.Appointment
import kotlinx.android.synthetic.main.fragment_appointment_detail.*

class AppointmentDetailActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_APPOINTMENT = "EXTRA_APPOINTMENT"

        fun newInstance(context: Context, appointment: Appointment): Intent {
            val intent = Intent(context, AppointmentDetailActivity::class.java)
            intent.putExtra(EXTRA_APPOINTMENT, appointment)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_detail)

        val appointment = intent.getSerializableExtra(EXTRA_APPOINTMENT) as Appointment

        supportActionBar?.title = appointment.customerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (fragmentManager.findFragmentById(R.id.appointment_detail_fragment) == null) {
            val fragment = AppointmentDetailFragment.newInstance(appointment)
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
