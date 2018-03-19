package com.projectx.fisioapp.repository


class RepositoryIntFakeImpl(): RepositoryInteractor {

    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = createFakeAuthenticateUser()
            success(token)
        } else {
            error("Error while authenticating user")
        }
    }

    override fun getToken(success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            val token = createFakeGetToken()
            success(token)
        } else {
            error("Error while getting token")
        }
    }

    fun createFakeAuthenticateUser(): String {
        val token = "AuthenticateUser12345"
        return token
    }

    fun createFakeGetToken(): String {
        val token = "GetToken12345"
        return token
    }

}
