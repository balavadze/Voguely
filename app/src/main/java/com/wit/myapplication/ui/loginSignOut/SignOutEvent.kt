package com.wit.myapplication.ui.loginSignOut

sealed class SignOutEvent {
    object SignOutSuccess : SignOutEvent()
    data class SignOutError(val message: String?) :SignOutEvent()
}