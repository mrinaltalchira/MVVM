package com.Mvvm.netWork

import com.Mvvm.auth.model.login.Login_Respo
import com.Mvvm.auth.model.signUp.Signup_respo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApii {

    @FormUrlEncoded
    @POST("login")
   suspend fun login(
        @Field("email") email:String,
        @Field("password")password:String
    ): Login_Respo

    @FormUrlEncoded
   @POST("register")
   suspend fun createUser(
       @Field ("username")username:String,
       @Field ("email")email:String,
       @Field ("password")password:String
       ): Signup_respo

}