package com.wit.myapplication.ui.login

sealed class LoginEvent {
    object LogInSuccess : LoginEvent()
    data class LoginError(val message: String?) : LoginEvent()
}

