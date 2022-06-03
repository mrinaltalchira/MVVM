package com.Mvvm.model.posts

data class Comment(
    val _id: String,
    val comment: String,
    val user: User
)