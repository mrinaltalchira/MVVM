package com.Mvvm.auth.authFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.Mvvm.auth.authActivity.R
import com.Mvvm.auth.authActivity.databinding.FragmentLoginBinding
import com.Mvvm.auth.factory.AuthViewModel
import com.Mvvm.netWork.AuthApii
import com.Mvvm.netWork.Reposetory.AuthRepo
import com.Mvvm.netWork.Resource
import com.Mvvm.ui.Activity.HomeActivity
import com.Mvvm.base.BaseFragment
import com.Mvvm.utils.enabled
import com.Mvvm.utils.handleApiError
import com.Mvvm.utils.startNewActivity
import com.Mvvm.utils.visiblee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class login : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepo>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnLogin.enabled(false)

        viewModel.loginRespo.observe(viewLifecycleOwner, Observer {
binding.progressDialog.visiblee(it is Resource.Loading)
            when (it) {

                is Resource.Success -> {

                    binding.progressDialog.visiblee(false)

                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.saveAuthToken(it.value.token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> {
                    binding.progressDialog.visiblee(false)
                    handleApiError(it)
                    Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressDialog.visiblee(true)
                }

            }

        })

        binding.tvPass.addTextChangedListener {

            var mail = binding.tvName.text.toString().trim()
            binding.btnLogin.enabled(mail.isNotEmpty() && it.toString().isNotEmpty())

        }

        binding.btnLogin.setOnClickListener {
            var mail = binding.tvName.text.toString()
            var pass = binding.tvPass.text.toString()

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            if (pass.isEmpty()) {
                binding.tvPass.error = "Empity Field is not allowed"
                return@setOnClickListener
            }
            if (!mail.matches(emailPattern.toRegex())) {
                binding.tvName.error = "Email pattern does not matched"
                return@setOnClickListener
            }

            viewModel.login(mail, pass)


        }

        binding.tvForget.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }


    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepo(remoteDataSource.buildApi(AuthApii::class.java), userPreferences)

}