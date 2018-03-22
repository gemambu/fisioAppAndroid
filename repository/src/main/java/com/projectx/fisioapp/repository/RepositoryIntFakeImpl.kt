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

    override fun registerUser(name: String, email: String, password: String, success: (ok: Boolean) -> Unit, error: (errorMessage: String) -> Unit) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        // check response from repository
        if (allOk) {
            var ok = true
            success(ok)
        } else {
            error("Error while getting registering user")
        }
    }

    fun createFakeAuthenticateUser(): String {
        val token = "AuthenticateUser12345"
        return token
    }

}
