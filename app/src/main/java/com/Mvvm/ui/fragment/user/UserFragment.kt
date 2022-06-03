package com.Mvvm.ui.fragment.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Mvvm.auth.authActivity.R
import com.Mvvm.auth.authActivity.databinding.UserFragmentBinding
import com.Mvvm.base.BaseFragment
import com.Mvvm.netWork.Reposetory.BaseRepo
import com.Mvvm.netWork.Reposetory.UserRepo
import com.Mvvm.netWork.UserApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserFragment : BaseFragment<UserViewModel,UserFragmentBinding,BaseRepo>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= UserFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): BaseRepo {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java , token.toString())
        return UserRepo(api)
    }
}