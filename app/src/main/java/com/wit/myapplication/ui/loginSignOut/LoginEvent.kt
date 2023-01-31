package com.wit.myapplication.ui.loginSignOut

sealed class LoginEvent {
    object LogInSuccess : LoginEvent()
    data class LoginError(val message: String?) : LoginEvent()
}




