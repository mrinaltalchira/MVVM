package com.Mvvm.netWork

import com.Mvvm.model.posts.Userpost
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("my/posts")
    suspend fun getPost(): Userpost
}