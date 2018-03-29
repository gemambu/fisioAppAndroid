package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getservice

import android.util.Log

import com.projectx.fisioapp.repository.entitymodel.responses.GetCatalogResponse
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import com.projectx.fisioapp.repository.entitymodel.catalog.converter.convert
import com.projectx.fisioapp.repository.entitymodel.responses.DeleteCatalogResponse
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppClient
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class DeleteServiceIntImpl(): DeleteServiceInteractor {
    override fun execute(token: String, id: String, success: (catalogItem: CatalogData) -> Unit, error: (errorMessage: String) -> Unit) {

        var apiInterfaceLocalhost: APIV1FisioAppInterface =
                APIV1FisioAppClient.client.create(APIV1FisioAppInterface::class.java)

        /**
         * Delete Service
         */
        val deleteService = apiInterfaceLocalhost.doDeleteService(token, id)
        deleteService.enqueue(object : Callback<DeleteCatalogResponse> {
            override fun onResponse(call: Call<DeleteCatalogResponse>, response: Response<DeleteCatalogResponse>) {
                val response = response.body()

                //response.let { success(convert(response!!)) }

            }

            override fun onFailure(call: Call<DeleteCatalogResponse>, t: Throwable?) {
                call.cancel()
                Log.d("App: ", t?.localizedMessage ?: "Connection to server not available")
                error(t?.localizedMessage ?: "Connection to server not available")
            }

        })
    }

}