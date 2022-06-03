package com.Mvvm.auth.model.login

data class Login_Respo(
    val existingUser: ExistingUser,
    val success: Boolean,
    val token: String?
)