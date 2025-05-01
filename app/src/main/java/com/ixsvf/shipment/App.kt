package com.ixsvf.shipment

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val applicationContext = applicationContext.applicationInfo.processName
        println("Application $applicationContext runnig")
    }
}