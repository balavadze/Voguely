package com.wit.myapplication.ui.login

import com.wit.myapplication.R

enum class SelectedTab(
    val welcomeMessage: Int,
    val buttonText: Int
) {
    LOGIN(
        welcomeMessage = R.string.welcome_back,
        buttonText = R.string.login
    ),
    SIGN_UP(
        welcomeMessage = R.string.join,
        buttonText = R.string.sign_up
    )
}