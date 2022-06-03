package com.Mvvm.netWork.Reposetory

import com.Mvvm.netWork.AuthApii
import com.Mvvm.userPreferences.UserPreferences

class AuthRepo(
    val apii :AuthApii,
    val preferences: UserPreferences
):BaseRepo() {

suspend fun login(email : String, password : String) = safeApiCall { apii.login(email,password)}

    suspend fun signUp(username:String,
                     email:String,
                       password:String
                     ) = safeApiCall { apii.createUser(username,email,password) }

    suspend fun saveAuthToken(token:String){
        preferences.saveAuthToken(token)
    }

}