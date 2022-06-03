package com.Mvvm.auth.model.login

data class ExistingUser(
    val __v: Int,
    val _id: String,
    val avatar: Avatar,
    val email: String,
    val followers: List<Any>,
    val following: List<Any>,
    val password: String,
    val posts: List<Post>,
    val username: String
)