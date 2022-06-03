package com.Mvvm.netWork.Reposetory

import com.Mvvm.netWork.UserApi

class UserRepo(
    val apii :UserApi
):BaseRepo() {

suspend fun getPost() = safeApiCall{ apii.getPost() }

}