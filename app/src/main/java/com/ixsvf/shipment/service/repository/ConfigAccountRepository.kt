package com.ixsvf.shipment.service.repository

import android.content.Context
import com.ixsvf.shipment.service.repository.remote.RetrofitClient
import com.ixsvf.shipment.service.repository.remote.endpoints.SessionLoginService
import com.ixsvf.shipment.service.repository.listener.APIListener
import com.ixsvf.shipment.service.repository.model.AccountModel


class ConfigAccountRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(SessionLoginService::class.java)

    companion object{
        private lateinit var repository : ConfigAccountRepository

        fun getInstance(context: Context): ConfigAccountRepository{
            if(!Companion::repository.isInitialized) {
                repository = ConfigAccountRepository(context)
            }
            return repository
        }
    }

    fun createAccount(account: AccountModel, listener: APIListener<String>){
        //ignore if connection is available for now
        val call = remote.postTestMessage(account)
        executeCall(call, listener)
    }

}