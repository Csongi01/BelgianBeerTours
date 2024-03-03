package com.example.guidedtours.util

import com.example.guidedtours.model.User

object MockupUser {
    private val user1 = User(0, "Csongi", "Csongor","Kapitány", "123", true)

    private val users = mutableListOf<User>(user1)

    fun getUser(userName: String?, password: String?) : User? {
        var user : User? = null
        var filterUsers = users.filter {
            it.userName.lowercase().equals(userName?.lowercase()) &&  it.password.equals(password)
        }
        if (!filterUsers.isEmpty()) {
            user = filterUsers[0]
        }
        return user
    }

    fun getUser(id : Int) : User? {
        try {
            return users.first {
                it.id == id
            }
        } catch (e: Exception) {
            return null
        }

    }
    fun getUsers() : List<User> {
        return users.toList()
    }
    fun addUser(user: User) {
        users.add(user)
    }
}



