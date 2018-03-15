package com.projectx.fisioapp.domain.interactor.users.gettoken

import android.content.Context
import android.util.Log
import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion
import com.projectx.fisioapp.repository.RepositoryIntImpl
import com.projectx.fisioapp.repository.RepositoryInteractor
import java.lang.ref.WeakReference


class GetTokenIntImpl(val context: Context) : GetTokenInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: RepositoryInteractor = RepositoryIntImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<String>, error: ErrorCompletion) {
        repository.getToken(
            success = {
                val token: String = it
                Log.d("App", "Pasa el it: $it")
                Log.d("App", "Pasa el token: $token")
                success.successCompletion(token)
            }, error = {
                error(it)
            }
        )
    }

}