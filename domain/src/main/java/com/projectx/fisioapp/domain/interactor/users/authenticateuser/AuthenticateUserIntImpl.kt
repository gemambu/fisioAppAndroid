package com.projectx.fisioapp.domain.interactor.users.authenticateuser

import android.content.Context
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.repository.RepositoryIntImpl
import com.projectx.fisioapp.repository.RepositoryInteractor
import java.lang.ref.WeakReference


class AuthenticateUserIntImpl (context: Context) :AuthenticateUserInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: RepositoryInteractor = RepositoryIntImpl(weakContext.get() !!)

    override fun execute(email: String, password: String, success: SuccessCompletion<String>, error: ErrorCompletion) {
        repository.authenticateUser(
                email, password,
                success = {
                    success.successCompletion(it)
                }, error = {
                    error(it)
                }
        )
    }

}