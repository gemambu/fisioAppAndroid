package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.fragment.CalendarFragment

class CalendarActivity : AppCompatActivity() {

    lateinit var calendarFragment: CalendarFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarFragment = supportFragmentManager.findFragmentById(R.id.calendar_fragment) as CalendarFragment
    }
}
