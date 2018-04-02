package com.projectx.fisioapp.app.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.projectx.fisioapp.app.settingsmanager.SettingsManager


fun ToastIt(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    Log.d("App", msg) // Debug
}

enum class CatalogType {
    SERVICE,
    PRODUCT
}

