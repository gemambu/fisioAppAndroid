package com.projectx.fisioapp.app.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.activity.CalendarActivity
import com.projectx.fisioapp.app.adapter.AppointmentItemRecyclerViewAdapter
import com.projectx.fisioapp.app.adapter.SimpleItemRecyclerViewAdapter
import com.projectx.fisioapp.domain.model.Appointment
import com.projectx.fisioapp.domain.model.Appointments
import kotlinx.android.synthetic.main.appointment_list.*

class AppointmentsListFragment : Fragment() {

    companion object {
        fun newInstance(): AppointmentsListFragment = AppointmentsListFragment()
    }


    lateinit var root: View
    private var appointments: Appointments? = null
    lateinit var mParent: CalendarActivity
    private var onSelectedAppointmentListener: OnSelectedAppointmentListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let{
            root = it.inflate(R.layout.fragment_appointments_list, container, false)
        }

        return root
    }

    fun setupRecyclerView(recyclerView: RecyclerView, list: Appointments) {

        recyclerView.adapter = AppointmentItemRecyclerViewAdapter(mParent, list)
        // set two columns with the elements
        recyclerView.layoutManager = GridLayoutManager(mParent.baseContext, 1) as RecyclerView.LayoutManager?
    }

    fun setAppointmentsList(appointments: Appointments){
        this.appointments = appointments
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onSelectedAppointmentListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnSelectedAppointmentListener) {
            onSelectedAppointmentListener = listener
        }
    }


    interface OnSelectedAppointmentListener {
        fun onSelectedAppointment(appointment: Appointment)
    }

    fun setParent(calendarActivity: CalendarActivity) {
        mParent = calendarActivity
    }

}
