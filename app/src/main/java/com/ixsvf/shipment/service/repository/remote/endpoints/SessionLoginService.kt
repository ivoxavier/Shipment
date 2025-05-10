package com.ixsvf.shipment.service.repository.remote.endpoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


data class SessionLoginBody(
    val account: String,
    val user: String,
    val password: String
)



interface SessionLoginService {
    @POST("http://192.168.1.73:8088/test")
    fun postTestMessage(@Body requestBody: SessionLoginBody): Call<Void>
}