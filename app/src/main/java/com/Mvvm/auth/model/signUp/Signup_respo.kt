package com.Mvvm.auth.model.signUp

data class Signup_respo(
    val success: Boolean,
    val token: String,
    val user: User
)