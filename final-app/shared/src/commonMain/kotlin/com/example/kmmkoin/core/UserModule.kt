package com.example.kmmkoin.core

import kotlinx.coroutines.flow.MutableStateFlow

data class User(val name: String)

class UserModule {
    val temp: MutableStateFlow<User?> = MutableStateFlow(null)
    private var _loggedInUsers: User? = null
        set(value) {
            println("Setting value -> ${value?.name}")
            temp.value = value
            field = value
        }
    val isLoggedIn: Boolean
        get() = _loggedInUsers != null
    val userName: String?
        get() {
            println("getting value -> ${_loggedInUsers?.name}")
            return _loggedInUsers?.name
        }
    val user: User?
        get() {
            println("getting user -> $_loggedInUsers")
            return _loggedInUsers
        }

    fun login(userName: String) {
        _loggedInUsers = User(userName)
    }

    fun logout() {
        _loggedInUsers = null
    }
}