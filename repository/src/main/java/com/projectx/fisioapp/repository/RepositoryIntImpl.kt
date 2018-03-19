package com.projectx.fisioapp.repository

import android.content.Context
import android.util.Log
import com.projectx.fisioapp.repository.cache.CacheIntFakeImpl
import com.projectx.fisioapp.repository.cache.CacheInteractor
import java.lang.ref.WeakReference


class RepositoryIntImpl(val context: Context) : RepositoryInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInteractor = CacheIntFakeImpl(weakContext.get() !!)

    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        Log.d("App", "Not implemented: authenticateUser in repository")
    }

    override fun getToken(success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        // Read token from cache
        cache.getToken(
                success = {
                    // if there's a token in cache --> return item
                    success(it)
                }, error = {
                    // if no token in cache --> network
                    error(it)
                }
        )

    }

}
