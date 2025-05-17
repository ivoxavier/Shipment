package com.ixsvf.shipment.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.ixsvf.shipment.viewmodel.ConfigAccountViewModel

@Composable
fun SimpleOutlineTextField(viewModel: ConfigAccountViewModel, textLabel: String, field: Int){
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
                    when(field){
                        1 -> viewModel.setAccount(it)
                        2 -> viewModel.setUser(it)
                    }
        },
        label = {Text(textLabel)},
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordTextField(viewModel: ConfigAccountViewModel, textLabel: String){
    var password by rememberSaveable { mutableStateOf("") }
    TextField(
        value = password,
        onValueChange = {
            password = it
            viewModel.setPassword(password)},
        label = {Text(textLabel)},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )
}