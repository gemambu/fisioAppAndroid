package com.projectx.fisioapp.app.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.projectx.fisioapp.R
import com.projectx.fisioapp.domain.model.Appointment
import com.projectx.fisioapp.domain.model.Appointments
import java.util.*

class AppointmentsListFragment : Fragment() {

    companion object {
        fun newInstance(): AppointmentsListFragment = AppointmentsListFragment()
    }


    lateinit var root: View
    lateinit var appointmentsList: ListView
    private var appointments: Appointments? = null
    private var list = emptyArray<Appointment>()
    lateinit var adapter: ArrayAdapter<Appointment>
    private var onSelectedAppointmentListener: OnSelectedAppointmentListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let{
            root = it.inflate(R.layout.fragment_appointments_list, container, false)
            appointmentsList = root.findViewById<ListView>(R.id.fisio_appointments_list)
            //adapter = ArrayAdapter<Appointment>(activity, android.R.layout.simple_list_item_1, appointments?.toArray())
            adapter = ArrayAdapter<Appointment>(activity, android.R.layout.simple_list_item_1, list)
            appointmentsList.adapter = adapter
            appointmentsList.setOnItemClickListener { parent, view, position, id ->
                val appointment = appointments?.get(position)
                onSelectedAppointmentListener?.onSelectedAppointment(appointment!!.date)
            }
        }

        return root
    }


    fun setAppointmentsList(appointments: Appointments){
        this.appointments = appointments
        adapter = ArrayAdapter<Appointment>(activity, android.R.layout.simple_list_item_1, this.appointments!!.toArray())
        appointmentsList.adapter = adapter
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
        fun onSelectedAppointment(date: Date)
    }

}
