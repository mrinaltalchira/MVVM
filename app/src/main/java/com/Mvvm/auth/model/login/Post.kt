package com.Mvvm.auth.model.login

data class Post(
    val __v: Int,
    val _id: String,
    val caption: String,
    val comments: List<Comment>,
    val createdAT: String,
    val createdAt: String,
    val image: Image,
    val likes: List<Any>,
    val owner: String
)