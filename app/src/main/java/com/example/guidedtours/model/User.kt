package com.example.guidedtours.model

import java.util.concurrent.atomic.AtomicInteger


data class User(
    var id: Int ,
    var userName: String,
    var firstName: String,
    var lastName: String,
    var password: String,
    var isActive: Boolean = false
) {

    constructor() : this(1, "", "", "", "", false)



    val fullName: String = "$firstName $lastName"

    object GlobalState {
        private var value = 0;

        fun getNextInt(): Int = value++
    }

}
