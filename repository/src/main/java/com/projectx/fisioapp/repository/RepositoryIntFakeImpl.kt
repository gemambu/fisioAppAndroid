package com.projectx.fisioapp.repository


class RepositoryIntFakeImpl(): RepositoryInteractor {

    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    fun createFakeGetToken(): String {
        val token = "token12345"
        return token
    }

}
