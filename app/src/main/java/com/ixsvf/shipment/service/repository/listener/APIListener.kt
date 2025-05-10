package com.ixsvf.shipment.service.repository.listener

interface APIListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)
}