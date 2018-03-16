package com.projectx.fisioapp.domain.interactor.users.gettoken

import com.projectx.fisioapp.domain.interactor.ErrorCompletion
import com.projectx.fisioapp.domain.interactor.SuccessCompletion


class GetTokenIntFakeImpl: GetTokenInteractor {
    override fun execute(success: SuccessCompletion<String>, error: ErrorCompletion) {

        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = createFakeGetToken()
            success.successCompletion(token)
        } else {
            error.errorCompletion("Error while getting token")
        }
    }

    fun createFakeGetToken(): String {
        val token = "token12345"
        return token
    }

}