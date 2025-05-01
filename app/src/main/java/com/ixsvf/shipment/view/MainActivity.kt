package com.ixsvf.shipment.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ixsvf.shipment.ui.theme.ShipmentTheme
import com.ixsvf.shipment.viewmodel.InstallState
import com.ixsvf.shipment.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            ShipmentTheme {
                NavigationDispatcher(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun NavigationDispatcher(viewModel: MainViewModel) {
    val installState by viewModel.installState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = (context as? ComponentActivity)

    LaunchedEffect(installState) {
        when (installState) {
            InstallState.Loading -> {
                //lOADING
            }
            InstallState.Clean -> {
                val intent = Intent(context, ConfigAccountActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            }
            InstallState.NotClean -> {
                //val intent = Intent(context, MenuActivity::class.java)
                //TODO: Remove line down after finish ConfigAccount
                val intent = Intent(context, ConfigAccountActivity::class.java)
                context.startActivity(intent)
                activity?.finish()
            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (installState == InstallState.Loading) {
            CircularProgressIndicator()
        }
        // let it as is, no need to change
    }
}
