package com.example.notzeezaibtech.ui.notes_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notzeezaibtech.R
import com.example.notzeezaibtech.ui.base.LocalNavHostController
import androidx.compose.foundation.background
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notzeezaibtech.ui.components.Routes
val selectedMenueItem: MutableState<String> = mutableStateOf("")

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun MyMenueScreen() {
    val navController = LocalNavHostController.current
    val context = LocalContext.current

    val menue = listOf("Privacy Policy", "Terms and Conditions", "Help and Support", "About", "Share")
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
    }, bottomBar = {
        Divider(
            color = colorResource(R.color.amlostBlack),
            thickness = 1.dp,
        )
    }) { innerPAdding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPAdding)
                .background(color = colorResource(id = R.color.black)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
            ){
                items(menue) { item ->
                    Card(
                        modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(10.dp),
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        ),
                    ) {
                        Button(
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                if (item == "Privacy Policy") {
                                    selectedMenueItem.value = "Privacy Policy"
                                   navController.navigate(Routes.PrivacyPolicy.name)
                                }
                                if (item == "Terms and Conditions") {
                                    selectedMenueItem.value = "Terms and Conditions"
                                   navController.navigate(Routes.PrivacyPolicy.name)
                                }
                                if (item == "Help and Support") {
                                    selectedMenueItem.value = "Help and Support"
                                   navController.navigate(Routes.PrivacyPolicy.name)
                                }
                                if (item == "About") {
                                    selectedMenueItem.value = "About"
                                   navController.navigate(Routes.PrivacyPolicy.name)

                                }

                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .height(80.dp)
                                .padding(10.dp),
                            colors = ButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Transparent,
                                disabledContentColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                            border = BorderStroke(
                                1.dp,
                                color = colorResource(R.color.amlostBlackUltra)
                            )

                        ) {
                            Text(item, color = Color.White)
                        }
                    }
                }


            }
        }

    }

}
