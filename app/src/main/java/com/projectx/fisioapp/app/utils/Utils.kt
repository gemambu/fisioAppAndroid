package com.projectx.fisioapp.app.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun ToastIt(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    Log.d("App", msg) // Debug
}

enum class CatalogType {
    SERVICE,
    PRODUCT
}

