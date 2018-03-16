package com.projectx.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.projectx.fisioapp.R


class BlankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        val msg = "Welcome Blank Activity!"
        Log.d("App", "$msg")
        Toast.makeText(baseContext, "$msg", Toast.LENGTH_LONG).show()
    }

}
