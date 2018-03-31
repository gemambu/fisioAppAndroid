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
import com.projectx.fisioapp.domain.model.Appointments
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import java.util.*

class CalendarActivity : /*AppCompatActivity(),*/ ParentActivity(), AppointmentsListFragment.OnSelectedAppointmentListener, CalendarFragment.OnSelectedDateListener {

    //private var list: Appointments? = null
    lateinit var calendarFragment: CalendarFragment
    lateinit var appointmentsListFragment: AppointmentsListFragment
    val myToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7Il9pZCI6IjVhOWYwNTRmNjAyZGQwZTU0MGM3MWJjNiIsImlzUHJvZmVzc2lvbmFsIjp0cnVlLCJmZWxsb3dzaGlwTnVtYmVyIjozMywiZ2VuZGVyIjoibWFsZSIsIm5hbWUiOiJmaXNpbyIsImxhc3ROYW1lIjoibGFzdG5hbWUiLCJlbWFpbCI6ImZpc2lvQGludmFsaWQuY29tIiwicGFzc3dvcmQiOiJlZjc5N2M4MTE4ZjAyZGZiNjQ5NjA3ZGQ1ZDNmOGM3NjIzMDQ4YzljMDYzZDUzMmNjOTVjNWVkN2E4OThhNjRmIiwiYWRkcmVzcyI6IkZpc2lvIEFkZHJlc3MsIDMzIiwicGhvbmUiOiI2MjY2MjY2MjYiLCJiaXJ0aERhdGUiOiIxOTcwLTEyLTMwVDEyOjMwOjAwLjAwMFoiLCJuYXRpb25hbElkIjoiMTIzNDU2NzhaIiwicmVnaXN0cmF0aW9uRGF0ZSI6IjIwMTgtMDEtMDFUMDE6MDE6MDAuMDAwWiIsImxhc3RMb2dpbkRhdGUiOiIyMDE4LTAzLTA3VDE2OjAwOjAwLjAwMFoiLCJfX3YiOjAsImRlbGV0ZWQiOmZhbHNlfSwiaWF0IjoxNTIyNDg4NDU4LCJleHAiOjE1MjI2NjEyNTh9.SQCXMk_apNOsiOC9kfAUxbzkdZ3kpvmrBDpX2bQ5pB8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*if (!checkToken()) {
            Router().navigateFromCalendarActivityToLoginActivity(this)
        } else {
            calendarFragment = supportFragmentManager.findFragmentById(R.id.calendar_fragment) as CalendarFragment
            appointmentsListFragment = supportFragmentManager.findFragmentById(R.id.appointments_fragment) as AppointmentsListFragment
        }*/

        calendarFragment = fragmentManager.findFragmentById(R.id.calendar_fragment) as CalendarFragment
        appointmentsListFragment = fragmentManager.findFragmentById(R.id.appointments_fragment) as AppointmentsListFragment
    }


    private fun getAppointmentsForDate(context: Context, date: String) {
        async(UI){

            val getAppointmentsForDate: GetAppointmentsForDateInteractor = GetAppointmentsForDateIntImpl(context)
            try{
                getAppointmentsForDate.execute(myToken, date,
                        success = object : SuccessCompletion<Appointments> {
                            override fun successCompletion(e: Appointments) {
                                //list = e
                                appointmentsListFragment.setAppointmentsList(e)
                            }
                        }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        ToastIt(baseContext, "$errorMessage")
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
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    // ***** Fragment AppointmentsList listener *****
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


    // ***** CalendarFragment listener *****
    override fun onSelectedDate(date: String) {
        getAppointmentsForDate(this, date)
    }
}
