package com.ixsvf.shipment.service.constants

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

class ShipmentConstants private constructor() {
    //region AppSettings
    object AppSettings {
        const val APP_SETTINGS_KEY = "appSettings"
        val IS_CLEAN_INSTALL: Preferences.Key<Boolean> = booleanPreferencesKey("is_clean_install")
    }
    //endregion

    //region LocalDB
    object LocalDB {

    }
    //endregion


    //API
    object API {
        object URL{
            const val BASE_URL = "http://192.168.221.251"
            const val TEST_SERVICE = "test"
        }
        object HTTP{
            object RESPONSE {
                const val SUCCESS = 200
            }
        }
    }



}