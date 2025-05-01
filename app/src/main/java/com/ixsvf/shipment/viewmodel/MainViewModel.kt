package com.ixsvf.shipment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ixsvf.shipment.service.repository.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Installs states
sealed interface InstallState {
    data object Loading : InstallState
    data object Clean : InstallState
    data object NotClean : InstallState
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appSettings = Settings(application)

    private val _installState = MutableStateFlow<InstallState>(InstallState.Loading)
    val installState: StateFlow<InstallState> = _installState.asStateFlow()

    init {
        checkInstallState()
    }

    private fun checkInstallState() {
        viewModelScope.launch {
            try {
                val isCurrentlyClean = appSettings.isCleanInstallFlow.first()

                if (isCurrentlyClean) {
                    _installState.value = InstallState.Clean
                } else {
                    _installState.value = InstallState.NotClean
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Err on checkInstallState", e)
                _installState.value = InstallState.NotClean
            }
        }
    }
}