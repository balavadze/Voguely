package com.wit.myapplication.ui.loginSignOut

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignOutViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    val _event = MutableSharedFlow<SignOutEvent>()

    fun signOut() {
        viewModelScope.launch {
            try {
                auth.signOut()
                _event.emit(SignOutEvent.SignOutSuccess)
            } catch (e: Exception) {
                _event.emit(SignOutEvent.SignOutError("Boooo"))
            }
        }

    }
}