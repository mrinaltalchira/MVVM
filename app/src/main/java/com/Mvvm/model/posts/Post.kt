package com.Mvvm.model.posts

data class Post(
    val __v: Int,
    val _id: String,
    val caption: String,
    val comments: List<Comment>,
    val createdAT: String,
    val image: Image,
    val likes: List<Any>,
    val owner: Owner
)