package com.projectx.fisioapp.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.projectx.fisioapp.repository.cache.CacheIntFakeImpl
import com.projectx.fisioapp.repository.cache.CacheInteractor
import com.projectx.fisioapp.repository.entitymodel.AuthenticateUserEntity
import com.projectx.fisioapp.repository.network.GetJsonManager
import com.projectx.fisioapp.repository.network.GetJsonManagerVolleyImpl
import com.projectx.fisioapp.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference


class RepositoryIntImpl(val context: Context) : RepositoryInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInteractor = CacheIntFakeImpl(weakContext.get() !!)

    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {

        // perform network request
        val url = BuildConfig.FISIOAPP_USERS_AUTHENTICATE_SERVER_URL
        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(url, success = object : SuccessCompletion<String> {
            override fun successCompletion(e: String) {

                val parser = JsonEntitiesParser()
                val responseEntity: AuthenticateUserEntity

                try {
                    responseEntity = parser.parse<AuthenticateUserEntity>(e)
                    success(responseEntity.token)
                } catch (e: InvalidFormatException) {
                    error("Error parsing")
                }

            }
        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                    error("Error getting data: " + errorMessage)
            }
        })

    }

    override fun getToken(success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        // Read token from cache
        cache.getToken(
                success = {
                    // if there's a token in cache --> return item
                    success(it)
                }, error = {
                    // if no token in cache --> network
                    error(it)
                }
        )

    }

}
