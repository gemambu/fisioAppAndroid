package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.projectx.fisioapp.R
import com.projectx.fisioapp.app.utils.ToastIt


class BlankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        val msg = "Welcome Blank Activity!"
        ToastIt(baseContext, 'd',"$msg")
    }

}
