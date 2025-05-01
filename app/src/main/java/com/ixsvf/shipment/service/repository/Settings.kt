package com.ixsvf.shipment.service.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ixsvf.shipment.service.constants.ShipmentConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Singleton instance of DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = ShipmentConstants.AppSettings.APP_SETTINGS_KEY)

class Settings(private val context: Context) {

    suspend fun isCleanInstall(value : Boolean){
        context.dataStore.edit { settings ->
            settings[ShipmentConstants.AppSettings.IS_CLEAN_INSTALL] = value

        }
    }

    val isCleanInstallFlow : Flow<Boolean> = context.dataStore.data
        .map { preferences ->
        preferences[ShipmentConstants.AppSettings.IS_CLEAN_INSTALL] ?: true
    }

}