package com.example.guidedtours.ui.Register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.guidedtours.util.MockupUser
import com.example.guidedtours.model.User


class RegistrationViewModel : ViewModel(
) {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    init {
    }


    fun updateUserName(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updateFirstName(firstname:String)
    {
        this.firstName = firstname
    }
    fun updateLastName(lastname:String)
    {
        this.lastName = lastname
    }
    fun register(user: User): Boolean
    {
        val users: MutableList<User> = MockupUser.getUsers().toMutableList()
        if ( user !=null && user.userName != "" && user.password != "" && user.firstName != "" && user.lastName != "" && users.none
            { it.userName == user.userName})
                {
                    MockupUser.addUser(user)
                    return true
                }
        return false
    }
    fun ClearTextFields()
    {
        firstName=""
        lastName=""
        password = ""
        username = ""

    }
}