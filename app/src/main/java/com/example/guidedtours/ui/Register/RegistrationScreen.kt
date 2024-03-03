package com.example.guidedtours.ui.Register


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.guidedtours.R
import com.example.guidedtours.model.User
import com.example.guidedtours.ui.Login.RegistrationButton
import com.example.guidedtours.ui.theme.GuidedToursScreenTheme



@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = viewModel(),
    modifier: Modifier = Modifier,
    loginSuccessFull: () -> Unit
) {
    var registrationMessage by remember { mutableStateOf<String?>(null) }
    var registrationMessageShow by remember { mutableStateOf(false) }
    GuidedToursScreenTheme {
        Scaffold(
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.register),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD2B48C)
                    ),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD2B48C))
                            .padding(16.dp)
                    ) {
                        // First Name
                        OutlinedTextField(
                            value = registrationViewModel.firstName,
                            onValueChange = { registrationViewModel.updateFirstName(it) },
                            label = { Text(stringResource(R.string.firstname)) },
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        // Last Name
                        OutlinedTextField(
                            value = registrationViewModel.lastName,
                            onValueChange = { registrationViewModel.updateLastName(it) },
                            label = { Text(stringResource(R.string.lastname)) },
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        // Username
                        OutlinedTextField(
                            value = registrationViewModel.username,
                            onValueChange = { registrationViewModel.updateUserName(it) },
                            label = { Text(stringResource(R.string.username)) },
                            leadingIcon = {
                                Icon(Icons.Default.AccountCircle, contentDescription = null)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        // Password
                        OutlinedTextField(
                            value = registrationViewModel.password,
                            onValueChange = { registrationViewModel.updatePassword(it) },
                            label = { Text(stringResource(R.string.password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = null)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        RegistrationButton(

                        onClick = {
                            var result =false
                             result=    registrationViewModel.register(
                               User(
                                        id= User.GlobalState.getNextInt(),
                                        registrationViewModel.username,
                                        registrationViewModel.firstName,
                                        registrationViewModel.lastName,
                                        registrationViewModel.password

                                    )
                                )

                                registrationMessageShow = true
                                if (registrationMessageShow) {
                                    if (result) {
                                        registrationViewModel.ClearTextFields()
                                        registrationMessage = "Registration Successful"
                                        loginSuccessFull()
                                        registrationMessageShow = false

                                    } else {
                                        registrationMessage =
                                            "Registration Failed. Please try again."
                                        registrationMessageShow = false
                                    }
                                }

                            }
                        )
                    }
                }
              registrationMessage?.let { message ->
                        Snackbar(
                            modifier = Modifier.padding(16.dp),
                            action = {
                                TextButton(onClick = { registrationMessage = null }) {
                                    Text(
                                        text = stringResource(R.string.Registration),
                                        color = Color.White
                                    )
                                }
                            },
                        ) {
                            Text(
                                text = message,
                                modifier = Modifier.padding(8.dp),
                                color = Color.White
                            )
                        }
                    }
            }
        }
    }
}




