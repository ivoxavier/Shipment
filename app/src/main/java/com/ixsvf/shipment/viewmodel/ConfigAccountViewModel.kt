package com.ixsvf.shipment.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ixsvf.shipment.service.repository.ConfigAccountRepository
import com.ixsvf.shipment.service.repository.listener.APIListener
import com.ixsvf.shipment.service.repository.model.AccountModel

class ConfigAccountViewModel(application: Application) : AndroidViewModel(application)  {


    private val configAccountRepository = ConfigAccountRepository(application)

    //region LiveData
    private val _account = MutableLiveData<String>()
    val account: MutableLiveData<String> = _account

    private val _user = MutableLiveData<String>()
    val user: MutableLiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> = _password
    //endregion




    fun setAccount(value: String){
        _account.value = value
        allSet["account"] = true
    }
    fun setUser(value: String){
        _user.value = value
        allSet["user"] = true
    }
    fun setPassword(value: String) {
        _password.value = value
        allSet["password"] = true
    }


    val allSet = mutableStateMapOf(
        "account" to false,
        "user" to false,
        "password" to false
    )


    fun allFieldsFilled(map: Map<String, Boolean>): Boolean {
        return map.values.all { it}
    }

    fun getToken(){
        val model = AccountModel().apply {
            account = _account.value.toString()
            user = _user.value.toString()
            password = _password.value.toString()
        }
        val listener = object : APIListener<String> {
            override fun onSuccess(result: String) {
                println("If you see this message 50% of the work is done")
            }

            override fun onFailure(message: String) {
                println("If you see this message 50% of the work is done")
            }

        }
        configAccountRepository.createAccount(model,listener)
    }
}
