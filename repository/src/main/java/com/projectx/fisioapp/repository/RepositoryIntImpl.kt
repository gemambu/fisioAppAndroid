package com.projectx.fisioapp.repository

import android.content.Context
import android.util.Log
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.projectx.fisioapp.repository.cache.CacheIntFakeImpl
import com.projectx.fisioapp.repository.cache.CacheIntImpl
import com.projectx.fisioapp.repository.cache.CacheInteractor
import com.projectx.fisioapp.repository.entitymodel.AuthenticateUserResponseEntity
import com.projectx.fisioapp.repository.network.GetJsonManager
import com.projectx.fisioapp.repository.network.GetJsonManagerVolleyImpl
import com.projectx.fisioapp.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference


class RepositoryIntImpl(val context: Context) : RepositoryInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val cache: CacheInteractor = CacheIntFakeImpl(weakContext.get() !!)
    //private val cache: CacheInteractor = CacheIntImpl(weakContext.get() !!)

    override fun authenticateUser(email: String, password: String, success: (token: String) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        //var url = BuildConfig.FISIOAPP_USERS_AUTHENTICATE_SERVER_URL
        var url = "data:text/json;charset=utf-8,%7B%0A%20%20%22ok%22%3A%20true%2C%0A%20%20%22token%22%3A%20%22eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7Il9pZCI6IjVhOWYwNTRmNjAyZGQwZTU0MGM3MWJjNiIsImlzUHJvZmVzc2lvbmFsIjp0cnVlLCJmZWxsb3dzaGlwTnVtYmVyIjozMywiZ2VuZGVyIjoibWFsZSIsIm5hbWUiOiJmaXNpbyIsImxhc3ROYW1lIjoibGFzdG5hbWUiLCJlbWFpbCI6ImZpc2lvQGludmFsaWQuY29tIiwicGFzc3dvcmQiOiJlZjc5N2M4MTE4ZjAyZGZiNjQ5NjA3ZGQ1ZDNmOGM3NjIzMDQ4YzljMDYzZDUzMmNjOTVjNWVkN2E4OThhNjRmIiwiYWRkcmVzcyI6IkZpc2lvIEFkZHJlc3MsIDMzIiwicGhvbmUiOiI2MjY2MjY2MjYiLCJiaXJ0aERhdGUiOiIxOTcwLTEyLTMwVDEyOjMwOjAwLjAwMFoiLCJuYXRpb25hbElkIjoiMTIzNDU2NzhaIiwicmVnaXN0cmF0aW9uRGF0ZSI6IjIwMTgtMDEtMDFUMDE6MDE6MDAuMDAwWiIsImxhc3RMb2dpbkRhdGUiOiIyMDE4LTAzLTA3VDE2OjAwOjAwLjAwMFoiLCJfX3YiOjAsImRlbGV0ZWQiOmZhbHNlfSwiaWF0IjoxNTIxNDE0OTMwLCJleHAiOjE1MjE1ODc3MzB9.FE0EDVlyCodTiB25Vp7BNBddIVX_gRHCswu8dtWRfMc%22%0A%7D"

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(url, success = object : SuccessCompletion<String> {
            override fun successCompletion(e: String) {

                val parser = JsonEntitiesParser()
                val responseEntity: AuthenticateUserResponseEntity

                try {
                    responseEntity = parser.parse<AuthenticateUserResponseEntity>(e)
                    Log.d("App", "RESPONSE?")
                    Log.d("App", responseEntity.toString())
                    Log.d("App", "AQUI: " + responseEntity)
                    success(responseEntity.result.token)
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
