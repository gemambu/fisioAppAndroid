package com.projectx.fisioapp.app.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.projectx.fisioapp.R

class AppointmentsListFragment : Fragment() {

    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let{
            root = it.inflate(R.layout.fragment_appointments_list, container, false)
        }

        return root
    }

}
