package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.getservice

import android.util.Log
<<<<<<< HEAD
import com.projectx.fisioapp.repository.entitymodel.catalog.GetCatalogResponse
=======

import com.projectx.fisioapp.repository.entitymodel.responses.GetCatalogResponse
>>>>>>> services_and_products_gema
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import com.projectx.fisioapp.repository.entitymodel.converter.convert
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppClient
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class GetServicesIntImpl (): GetProductsInteractor {
    override fun execute(token: String, success: (catalogItems: List<CatalogData>) -> Unit, error: (errorMessage: String) -> Unit) {

        var apiInterfaceLocalhost: APIV1FisioAppInterface =
                APIV1FisioAppClient.client.create(APIV1FisioAppInterface::class.java)

        /**
         * Get Services
         */
        val callGetServices = apiInterfaceLocalhost.doGetServices(token)
        callGetServices.enqueue(object : Callback<GetCatalogResponse> {
            override fun onResponse(call: Call<GetCatalogResponse>, response: Response<GetCatalogResponse>) {
                val response = response.body()

                response.let { success(convert(response!!)) }

            }

            override fun onFailure(call: Call<GetCatalogResponse>, t: Throwable?) {
                call.cancel()
                Log.d("App: ", t?.localizedMessage ?: "Connection to server not available")
                error(t?.localizedMessage ?: "Connection to server not available")
            }

        })
    }

}