package com.projectx.fisioapp.app.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.models.Appointment
import com.projectx.fisioapp.app.models.Appointments
import java.util.*

class AppointmentsListFragment : Fragment() {

    companion object {
        fun newInstance(): AppointmentsListFragment = AppointmentsListFragment()
    }

    lateinit var root: View
    private var onSelectedAppointmentListener: OnSelectedAppointmentListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let{
            root = it.inflate(R.layout.fragment_appointments_list, container, false)
            val list = root.findViewById<ListView>(R.id.fisio_appointments_list)
            val adapter = ArrayAdapter<Appointment>(activity, android.R.layout.simple_list_item_1, Appointments.toArray())

            list.adapter = adapter
            list.setOnItemClickListener { parent, view, position, id ->
                val appointment = Appointments.get(position)
                onSelectedAppointmentListener?.onSelectedAppointment(appointment.date)
            }
        }

        return root
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
