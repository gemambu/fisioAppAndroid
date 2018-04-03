package com.projectx.fisioapp.repository.network.apifisioapp.apiv1.deleteAppointment

import android.util.Log
import com.projectx.fisioapp.repository.entitymodel.appointments.AppoinmentData
import com.projectx.fisioapp.repository.entitymodel.appointments.DeleteAppointmentResponse
import com.projectx.fisioapp.repository.entitymodel.appointments.converterDeleteAppointment
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppClient
import com.projectx.fisioapp.repository.network.apifisioapp.apiv1.APIV1FisioAppInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal  class DeleteAppointmentIntImpl: DeleteAppointmentInteractor {
    override fun execute(token: String, id: String, success: (result: Boolean) -> Unit, error: (errorMessage: String) -> Unit) {

        var apiInterfaceLocalhost: APIV1FisioAppInterface =
                APIV1FisioAppClient.client.create(APIV1FisioAppInterface::class.java)

        val deleteAppointment = apiInterfaceLocalhost.doDeleteAppointment(token, id)
        deleteAppointment.enqueue(object : Callback<DeleteAppointmentResponse> {

            override fun onResponse(call: Call<DeleteAppointmentResponse>?, response: Response<DeleteAppointmentResponse>) {
                val response = response.body()

                response.let { success(converterDeleteAppointment(response!!)) }
            }

            override fun onFailure(call: Call<DeleteAppointmentResponse>, t: Throwable?) {
                call.cancel()
                Log.d("App: ", t?.localizedMessage ?: "Connection to server not available")
                error(t?.localizedMessage ?: "Connection to server not available")
            }



        })
    }

}
