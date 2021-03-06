package com.Mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.Mvvm.auth.factory.VMFactory
import com.Mvvm.netWork.RemoteDataSource
import com.Mvvm.netWork.Reposetory.BaseRepo
import com.Mvvm.userPreferences.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : ViewModel, B : ViewBinding, R : BaseRepo> : Fragment() {

    lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        userPreferences = UserPreferences(requireActivity())
        val factory = VMFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        var token = lifecycleScope.launch { userPreferences.authToken.first() }

        return binding.root
    }

    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun getFragmentRepository(): R

}