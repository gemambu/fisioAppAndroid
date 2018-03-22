package com.projectx.fisioapp.repository

import android.content.Context
import com.projectx.fisioapp.repository.cache.CacheIntFakeImpl
import com.projectx.fisioapp.repository.cache.CacheInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.authenticateuser.AuthenticateUserIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.authenticateuser.AuthenticateUserInteractor
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.registeruser.RegisterUserIntImpl
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.registeruser.RegisterUserInteractor
import java.lang.ref.WeakReference


class RepositoryIntImpl(val context: Context) : RepositoryInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInteractor = CacheIntFakeImpl(weakContext.get() !!)
    private val authenticateUser: AuthenticateUserInteractor = AuthenticateUserIntImpl()
    private val registerUser: RegisterUserInteractor = RegisterUserIntImpl()

    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {

        // perform network request
        authenticateUser.execute(email, password,
                success = {
                    success(it)
                }, error = {
                    error(it)
                }
        )

    }

    override fun registerUser(name: String, email: String, password: String, success: (ok: Boolean) -> Unit, error: (errorMessage: String) -> Unit) {

        // perform network request
        registerUser.execute(name, email, password,
                success = {
                    success(it)
                }, error = {
                    error(it)
                }
        )

    }

}
