package com.projectx.fisioapp.app.fragment

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectx.fisioapp.R

class AppointmentDetailFragment : Fragment() {

    companion object {
        private val EXTRA_APPOINTMENT = "EXTRA_APPOINTMENT"

        fun newInstance()/*(appointment: Appointment)*/: AppointmentDetailFragment{
            val fragment = AppointmentDetailFragment()
            //val arguments = Bundle()
            //arguments.putSerializable(EXTRA_APPOINTMENT, appointment)
            //fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root: View


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        inflater?.let{
            root = it.inflate(R.layout.fragment_appointment_detail, container, false)
        }

        return root
    }

}
