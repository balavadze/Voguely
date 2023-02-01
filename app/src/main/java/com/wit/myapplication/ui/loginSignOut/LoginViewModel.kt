package com.wit.myapplication.ui.loginSignOut

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    private val _selectedTab = MutableStateFlow(SelectedTab.LOGIN)
    val selectedTab: StateFlow<SelectedTab> = _selectedTab.asStateFlow()

    fun onSelectedTabChanged(selectedTab: SelectedTab) {
        viewModelScope.launch {
            _selectedTab.value = selectedTab
        }
    }

    fun onButtonClick(email: String, password: String) {
        viewModelScope.launch {
            when (selectedTab.value) {
                SelectedTab.LOGIN -> logIn(email, password)
                SelectedTab.SIGN_UP -> signUp(email, password)
            }
        }
    }

    private fun logIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _event.emit(LoginEvent.LogInSuccess)
/*                _loginSuccessEvent.value = true*/
            } catch (e: FirebaseAuthInvalidUserException) {
                _event.emit(LoginEvent.LoginError("User does not exist"))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _event.emit(LoginEvent.LoginError("Invalid email or password"))
            } catch (e: Exception) {
                _event.emit(LoginEvent.LoginError("An error occurred, please try again"))
            }
        }
    }

    private fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _event.emit(LoginEvent.LogInSuccess)
                /*_loginSuccessEvent.value = true*/
            } catch (e: FirebaseAuthWeakPasswordException) {
                _event.emit(LoginEvent.LoginError("Password should be at least 6 characters"))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _event.emit(LoginEvent.LoginError("Invalid email address"))
            } catch (e: FirebaseAuthUserCollisionException) {
                _event.emit(LoginEvent.LoginError("User with this email already exists"))
            } catch (e: Exception) {
                _event.emit(LoginEvent.LoginError("An error occurred, please try again"))
            }
        }

    }


}

