package com.ixsvf.shipment.view.components

import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmButton(label: String, onClick: () -> Unit){
    ElevatedButton(onClick = { onClick() }) {
        Text(label)
    }
}