package com.wit.myapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val _selectedTab = MutableStateFlow(SelectedTab.LOGIN)
    val selectedTab: StateFlow<SelectedTab> = _selectedTab.asStateFlow()

    fun onSelectedTabChanged(selectedTab: SelectedTab) {
        viewModelScope.launch {
            _selectedTab.value = selectedTab

        }
    }
}