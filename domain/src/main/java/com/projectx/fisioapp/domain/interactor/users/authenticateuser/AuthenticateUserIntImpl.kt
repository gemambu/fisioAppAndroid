package com.projectx.fisioapp.domain.interactor.users.authenticateuser

import android.content.Context
import android.util.Log
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.repository.RepositoryIntFakeImpl
import com.projectx.fisioapp.repository.RepositoryIntImpl
import com.projectx.fisioapp.repository.RepositoryInteractor
import java.lang.ref.WeakReference


class AuthenticateUserIntImpl (val context: Context) :AuthenticateUserInteractor {


    private val weakContext = WeakReference<Context>(context)
    private val repository: RepositoryInteractor = RepositoryIntImpl(weakContext.get() !!)

    override fun execute(email: String, password: String, success: SuccessCompletion<String>, error: ErrorCompletion) {
        repository.authenticateUser(
                email, password,
                success = {
                    val token: String = it
                    success.successCompletion(token)
                }, error = {
                    error(it)
                }
        )
    }

}