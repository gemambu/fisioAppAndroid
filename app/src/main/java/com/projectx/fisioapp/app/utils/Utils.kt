package com.projectx.fisioapp.app.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun ToastIt(context: Context, logType: Char, msg: String) {

    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    when (logType) {
        'd' -> Log.d("App", msg) // Debug
        'e' -> Log.e("App", msg) // Assert
        'i' -> Log.i("App", msg) // Info
        'v' -> Log.v("App", msg) // Verbose
        'w' -> Log.w("App", msg) // Warm
        else -> Log.d("App", msg)
    }
}
