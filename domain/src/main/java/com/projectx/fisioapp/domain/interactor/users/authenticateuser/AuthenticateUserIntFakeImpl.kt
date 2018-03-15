package com.projectx.fisioapp.domain.interactor.users.authenticateuser

import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion

class AuthenticateUserIntFakeImpl : AuthenticateUserInteractor {
    override fun execute(email: String, password: String, success: SuccessCompletion<String>, error: ErrorCompletion) {

        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = createFakeUserAuthentication()
            success.successCompletion(token)
        } else {
            error.errorCompletion("Error while authenticating user")
        }
    }

    fun createFakeUserAuthentication(): String {
        val token = "token12345"
        return token
    }


}
