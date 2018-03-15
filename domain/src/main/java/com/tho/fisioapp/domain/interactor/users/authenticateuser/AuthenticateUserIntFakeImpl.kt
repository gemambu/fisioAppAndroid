package com.tho.fisioapp.domain.interactor.users.authenticateuser

import com.tho.fisioapp.domain.interactor.ErrorCompletion
import com.tho.fisioapp.domain.interactor.SuccessCompletion

class AuthenticateUserIntFakeImpl : AuthenticateUserInteractor {
    override fun execute(email: String, password: String, success: SuccessCompletion<String>, error: ErrorCompletion) {

        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = createFakeActivityDetail()
            success.successCompletion(token)
        } else {
            error.errorCompletion("Error while authenticating user")
        }
    }

    fun createFakeActivityDetail(): String {
        val token = "token12345"
        return token
    }


}
