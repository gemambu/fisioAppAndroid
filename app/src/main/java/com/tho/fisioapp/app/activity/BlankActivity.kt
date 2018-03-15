package com.tho.fisioapp.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tho.fisioapp.R

class BlankActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        Log.d("App", "BlankActivity --> Navigation from LoginActivity to BlankActivity")
    }
}
