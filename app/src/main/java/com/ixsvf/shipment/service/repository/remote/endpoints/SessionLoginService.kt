package com.ixsvf.shipment.service.repository.remote.endpoints

import com.ixsvf.shipment.service.constants.ShipmentConstants
import com.ixsvf.shipment.service.repository.model.AccountModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path



interface SessionLoginService {
    @POST(ShipmentConstants.API.URL.TEST_SERVICE)
    fun postTestMessage(@Body requestBody: AccountModel): Call<String>
}