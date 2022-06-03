package com.Mvvm.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Mvvm.model.posts.Userpost
import com.Mvvm.netWork.Reposetory.UserRepo
import com.Mvvm.netWork.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: UserRepo) : ViewModel() {

    val _user: MutableLiveData<Resource<Userpost>> = MutableLiveData()
    val user: LiveData<Resource<Userpost>>
        get() = _user

    fun getPost() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repo.getPost()
    }

}