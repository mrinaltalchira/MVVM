package com.Mvvm.auth.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Mvvm.auth.model.login.Login_Respo
import com.Mvvm.auth.model.signUp.Signup_respo
import com.Mvvm.netWork.Reposetory.AuthRepo
import com.Mvvm.netWork.Resource
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepo
) : ViewModel() {

//    var loading = MutableLiveData<Boolean>()


    // login Function

    private val _loginRespo: MutableLiveData<Resource<Login_Respo>> = MutableLiveData()
    val loginRespo: LiveData<Resource<Login_Respo>>
        get() = _loginRespo

    fun login(email: String, password: String) = viewModelScope.launch {

        _loginRespo.value = repository.login(email, password)
    }

    // Signup Function

    private val _signupRespo: MutableLiveData<Resource<Signup_respo>> = MutableLiveData()
    val signupRespo: LiveData<Resource<Signup_respo>>
        get() = _signupRespo

    fun signUp(
        username: String,
        email: String,
        password: String
    ) = viewModelScope.launch {

        _signupRespo.value =
            repository.signUp(username, email, password)
    }

   suspend fun saveAuthToken(token: String){
       repository.saveAuthToken(token)

   }
}
