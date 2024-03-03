package com.example.guidedtours.ui.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.guidedtours.util.MockupUser
import com.example.guidedtours.data.MyConfiguration
import com.example.guidedtours.data.MyConfiguration.loggedInUser

class LoginViewModel: ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    init {
    }


    fun updateUserName(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun login() : Boolean {
        MyConfiguration.loggedInUser = MockupUser.getUser(username, password)
        if (loggedInUser != null) {
            password = ""
            username = ""
        }
        return MyConfiguration.loggedInUser != null
    }
}
