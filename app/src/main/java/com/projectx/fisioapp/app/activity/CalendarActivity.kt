package com.projectx.fisioapp.app.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.AppointmentsListFragment
import com.projectx.fisioapp.app.fragment.CalendarFragment
import com.projectx.fisioapp.app.router.Router
import com.projectx.fisioapp.app.utils.ToastIt
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.domain.interactor.appointments.GetAppointmentsForDateIntImpl
import com.projectx.fisioapp.domain.interactor.appointments.GetAppointmentsForDateInteractor
import com.projectx.fisioapp.domain.model.Appointment
import com.projectx.fisioapp.domain.model.Appointments
import kotlinx.android.synthetic.main.appointment_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class CalendarActivity : ParentActivity(),
        AppointmentsListFragment.OnSelectedAppointmentListener,
        CalendarFragment.OnSelectedDateListener {


    private lateinit var calendarFragment: CalendarFragment
    lateinit var appointmentsListFragment: AppointmentsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        if (!checkToken()) {
            Router().navigateFromCalendarActivityToLoginActivity(this)
        }

        title = getString(R.string.calendar_title)


    }

    override fun onResume() {
        super.onResume()

        calendarFragment = fragmentManager.findFragmentById(R.id.calendar_fragment) as CalendarFragment
        appointmentsListFragment = fragmentManager.findFragmentById(R.id.appointments_fragment) as AppointmentsListFragment
        appointmentsListFragment.setParent(this)
    }

    private fun getAppointmentsForDate(context: Context, date: String) {
        async(UI){

            val getAppointmentsForDate: GetAppointmentsForDateInteractor = GetAppointmentsForDateIntImpl(context)
            try{
                getAppointmentsForDate.execute(token, date,
                        success = object : SuccessCompletion<Appointments> {
                            override fun successCompletion(e: Appointments) {
                                appointmentsListFragment.setAppointmentsList(e.appointments)
                                appointmentsListFragment.setupRecyclerView(appointment_list, e)

                                //appointmentsListFragment.setupRecyclerView(appointment_list, e.appointments)
                            }
                        }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        ToastIt(baseContext, errorMessage)
                    }
                })
            } catch (e: Exception) {
                ToastIt(context, "Error: " + e.localizedMessage)
            }
        }
    }

    // ***** Back button enabled *****
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    // ***** Fragment AppointmentsList listener *****
    override fun onSelectedAppointment(appointment: Appointment) {
        Router().navigateFromCalendarActivityToAppointmentDetailActivity(this, appointment)
    }

    // ***** CalendarFragment listener *****
    override fun onSelectedDate(date: String) {
        getAppointmentsForDate(this, date)
    }

}
