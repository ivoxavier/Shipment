package com.ixsvf.shipment.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ConfigAccountViewModel(application: Application) : AndroidViewModel(application)  {


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
}
