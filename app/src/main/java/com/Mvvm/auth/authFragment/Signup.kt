package com.Mvvm.auth.authFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.Mvvm.auth.authActivity.R
import com.Mvvm.auth.authActivity.databinding.FragmentSignupBinding
import com.Mvvm.auth.factory.AuthViewModel
import com.Mvvm.netWork.AuthApii
import com.Mvvm.netWork.Reposetory.AuthRepo
import com.Mvvm.netWork.Resource
import com.Mvvm.base.BaseFragment


class Signup : BaseFragment<AuthViewModel, FragmentSignupBinding, AuthRepo>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        viewModel.signupRespo.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {
                    binding.progressDialog.visibility = View.GONE
                    findNavController().navigate(R.id.action_signup_to_login)

                }
                is Resource.Failure -> {
                    binding.progressDialog.visibility = View.GONE

                    Toast.makeText(requireActivity(), "${it.errorBody}", Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.btnSignup.setOnClickListener {
            binding.progressDialog.visibility = View.VISIBLE
            var userName = binding.tvUserName.text.toString()
            var email = binding.tvEmail.text.toString()
            var passWord = binding.tvPass.text.toString()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            if (userName.isEmpty()) {
                binding.tvUserName.error = "Empity Field is not allowed"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.tvEmail.error = "Empity Field is not allowed"
                return@setOnClickListener
            }
            if (passWord.isEmpty()) {
                binding.tvPass.error = "Empity Field is not allowed"
                return@setOnClickListener
            }
            if (!email.matches(emailPattern.toRegex())) {
            binding.tvEmail.error = "Email pattern does not matched"
            return@setOnClickListener
        }

            viewModel.signUp(userName,email,passWord)

        }

        binding.tvForget.setOnClickListener { requireActivity().onBackPressed() }
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignupBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepo(remoteDataSource.buildApi(AuthApii::class.java), userPreferences)

}