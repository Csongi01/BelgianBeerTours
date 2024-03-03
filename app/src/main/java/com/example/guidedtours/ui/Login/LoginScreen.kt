package com.example.guidedtours.ui.Login


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guidedtours.R
import com.example.guidedtours.ui.theme.GuidedToursScreenTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), loginSuccessFull: () -> Unit, modifier: Modifier = Modifier, Register: () -> Unit) {
    GuidedToursScreenTheme {
        Scaffold() {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(40.dp)
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UsernameTextField(
                        userName = loginViewModel.username,
                        onValueChange = { loginViewModel.updateUserName(it) })
                    Spacer(Modifier.height(10.dp))
                    PasswordTextField(
                        password = loginViewModel.password,
                        onValueChange = { loginViewModel.updatePassword(it) })
                    Spacer(Modifier.height(10.dp))
                    Row {
                        LoginButton {
                            if (loginViewModel.login()) {
                                loginSuccessFull()
                            }
                        }
                        RegistrationButton {
                            Register()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun UsernameTextField(userName: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = userName,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(R.string.username)) },
        leadingIcon = {
            Icon(Icons.Default.AccountCircle, contentDescription = null)
        }
    )
}

@Composable
fun PasswordTextField(password: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(R.string.password)) },
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(Icons.Default.Lock, contentDescription = null)
        }
    )
}

@Composable
fun LoginButton(onClick : () -> Unit) {
    Button(onClick = onClick,
        Modifier.background(Color(0xFF8B4513)) ) {
        Text(stringResource(R.string.login))
    }
}
@Composable
fun RegistrationButton(onClick : () -> Unit) {
    Button(onClick = onClick,
        Modifier.background(Color(0xFF8B4513))) {

        Text(stringResource(R.string.register))
    }
}
