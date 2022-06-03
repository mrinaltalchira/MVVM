package com.Mvvm.model.posts

data class Owner(
    val __v: Int,
    val _id: String,
    val avatar: AvatarX,
    val email: String,
    val followers: List<Any>,
    val following: List<Any>,
    val password: String,
    val posts: List<String>,
    val username: String
)