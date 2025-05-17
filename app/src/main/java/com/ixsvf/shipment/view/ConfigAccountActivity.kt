package com.ixsvf.shipment.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.ixsvf.shipment.R
import com.ixsvf.shipment.ui.theme.ShipmentTheme
import com.ixsvf.shipment.view.components.ConfirmButton
import com.ixsvf.shipment.view.components.PasswordTextField
import com.ixsvf.shipment.view.components.SimpleOutlineTextField
import com.ixsvf.shipment.view.components.TopTitles
import com.ixsvf.shipment.viewmodel.ConfigAccountViewModel

class ConfigAccountActivity: ComponentActivity() {
    private lateinit var viewModel: ConfigAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ConfigAccountViewModel::class.java]
        setContent {
            ShipmentTheme {
                LazyColumn(modifier = Modifier.fillMaxSize()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    item{
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            TopTitles(label = resources.getString(R.string.top_title_config_account))
                        }
                    }
                    item{
                        SimpleOutlineTextField(viewModel,resources.getString(R.string.label_customer_account),1)
                    }
                    item{
                        SimpleOutlineTextField(viewModel,resources.getString(R.string.label_user_account),2)
                    }
                    item{
                        PasswordTextField(viewModel,resources.getString(R.string.label_password_account))
                }

                    item{
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                            ConfirmButton(label = resources.getString(R.string.label_btn_get_token),onClick={
                                if(viewModel.allFieldsFilled(viewModel.allSet)){
                                    viewModel.getToken()
                                }
                            })
                        }

                    }

            }
        }
    }
}
}