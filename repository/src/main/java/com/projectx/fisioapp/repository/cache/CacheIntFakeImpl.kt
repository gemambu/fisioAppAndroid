package com.projectx.fisioapp.repository.cache

import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference


class CacheIntFakeImpl(val context: Context): CacheInteractor{
    private val weakContext = WeakReference<Context>(context)

    // Users

}