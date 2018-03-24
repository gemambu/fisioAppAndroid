package com.projectx.fisioapp.domain.interactor.users.registeruser

import android.content.Context
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.repository.RepositoryIntImpl
import com.projectx.fisioapp.repository.RepositoryInteractor
import java.lang.ref.WeakReference

class RegisterUserIntImpl(context: Context) : RegisterUserInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: RepositoryInteractor = RepositoryIntImpl(weakContext.get() !!)

    override fun execute(name: String, email: String, password: String, success: SuccessCompletion<Boolean>, error: ErrorCompletion) {
        repository.registerUser(
                name, email, password,
                success = {
                    success.successCompletion(it)
                }, error = {
            error(it)
        }
        )
    }

}