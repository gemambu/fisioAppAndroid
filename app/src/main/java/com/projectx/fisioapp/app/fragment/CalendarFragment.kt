package com.projectx.fisioapp.app.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.projectx.fisioapp.R
import java.util.*

class CalendarFragment : Fragment() {

    lateinit var root: View
    lateinit var calendarView: CalendarView
    private var onSelectedDateListener: OnSelectedDateListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let{
            root = it.inflate(R.layout.fragment_calendar, container, false)
            calendarView = root.findViewById(R.id.fisio_calendar)
            //calendarView.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            calendarView.firstDayOfWeek = 2

            setCalendarListener(calendarView)
        }

        return root
    }


    fun setCalendarListener(calendar: CalendarView){
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            //Note that months are indexed from 0. So, 0 means january, 1 means February, 2 means march etc.
            /*val sb = StringBuilder()
            sb.append(dayOfMonth).append(month + 1).append(year)
            val date = sb.toString()*/
            val month = month + 1
            val date: String = "$year-$month-$dayOfMonth) "
            onSelectedDateListener?.onSelectedDate(date)
        }
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
        onSelectedDateListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnSelectedDateListener) {
            onSelectedDateListener = listener
        }
    }

    interface OnSelectedDateListener {
        fun onSelectedDate(date: String)
    }






}
