package com.ixsvf.shipment.service.repository

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.google.gson.Gson
import com.ixsvf.shipment.R
import androidx.annotation.RequiresPermission
import com.ixsvf.shipment.service.constants.ShipmentConstants
import com.ixsvf.shipment.service.repository.listener.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {
    private fun failResponse(str : String):String{
        return Gson().fromJson(str,String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>){
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.code() == ShipmentConstants.API.HTTP.RESPONSE.SUCCESS){
                    val responseBody = response.body()
                    Log.d("API_RESPONSE_SUCCESS", "Raw response: ${Gson().toJson(responseBody)}")
                    response.body()?.let { listener.onSuccess(it) }
                } else{
                    val errorBody = response.errorBody()?.string()
                    Log.e("API_RESPONSE_ERROR", "Error code: ${response.code()}, Raw error: $errorBody")
                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.e("API_RESPONSE_FAILURE", "Failure: ${t.message}")
                listener.onFailure(context.getString(R.string.api_msg_unexpected_error))
            }

        })
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isConnectionAvailable(): Boolean{
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNet = cm.activeNetwork ?: return false
        val netWorkCapabilites = cm.getNetworkCapabilities(activeNet) ?: return false

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = when {
                netWorkCapabilites.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                netWorkCapabilites.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        else{
            if(cm.activeNetworkInfo != null){
                //deprecated
                result = when (cm.activeNetworkInfo!!.type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }

}