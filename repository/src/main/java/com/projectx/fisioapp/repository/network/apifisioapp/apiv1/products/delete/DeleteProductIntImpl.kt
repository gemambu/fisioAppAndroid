package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.products.delete

import android.util.Log

import com.projectx.fisioapp.repository.entitymodel.responses.DeleteCatalogResponse
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppClient
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class DeleteProductIntImpl(): DeleteProductInteractor {
    override fun execute(token: String, id: String, success: (successMessage: String) -> Unit, error: (errorMessage: String) -> Unit) {

        var apiInterfaceLocalhost: APIV1FisioAppInterface =
                APIV1FisioAppClient.client.create(APIV1FisioAppInterface::class.java)

        /**
         * Delete Product
         */
        val deleteProduct = apiInterfaceLocalhost.doDeleteProduct(token, id)
        deleteProduct.enqueue(object : Callback<DeleteCatalogResponse> {
            override fun onResponse(call: Call<DeleteCatalogResponse>, response: Response<DeleteCatalogResponse>) {
                val response = response.body()
                if (response !== null) response.message.let { success(response.message!!) }
            }

            override fun onFailure(call: Call<DeleteCatalogResponse>, t: Throwable?) {
                call.cancel()
                Log.d("App: ", t?.localizedMessage ?: "Connection to server not available")
                error(t?.localizedMessage ?: "Connection to server not available")
            }

        })
    }

}