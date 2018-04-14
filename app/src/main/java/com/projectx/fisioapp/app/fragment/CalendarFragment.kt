package com.projectx.fisioapp.app.fragment

//import android.support.v4.app.Fragment
import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.projectx.fisioapp.R
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class CalendarFragment : Fragment() {

    private var calendarView: CalendarView? = null
    private var onSelectedDateListener: OnSelectedDateListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater?.inflate(R.layout.fragment_calendar, container, false)
        calendarView = root?.findViewById(R.id.fisio_calendar)
        calendarView?.firstDayOfWeek = 2

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            //Note that months are indexed from 0. So, 0 means january, 1 means February, 2 means march etc.
            val month = month + 1
            val date = "$year-$month-$dayOfMonth"

            //val parsedDate = Date(year, month, dayOfMonth)


            onSelectedDateListener?.onSelectedDate(date)
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
        onSelectedDateListener = null
    }

    private fun commonAttach(listener: Any?) {
        if (listener is OnSelectedDateListener) {
            onSelectedDateListener = listener
        }
    }

    interface OnSelectedDateListener {
        fun onSelectedDate(date: String)
    }


}
