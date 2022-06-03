package com.Mvvm.auth.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Mvvm.netWork.Reposetory.BaseRepo
import com.Mvvm.netWork.Reposetory.AuthRepo
import com.Mvvm.netWork.Reposetory.UserRepo
import com.Mvvm.ui.fragment.explore.ExploreViewModel
import com.Mvvm.ui.fragment.home.HomeViewModel
import com.Mvvm.ui.fragment.user.UserViewModel

class VMFactory(private val baseRepo: BaseRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(baseRepo as AuthRepo) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(baseRepo as UserRepo) as T
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> ExploreViewModel(baseRepo as UserRepo) as T
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(baseRepo as UserRepo) as T
            else -> throw IllegalArgumentException("ViewModelClass ot Found")
        }
    }
}