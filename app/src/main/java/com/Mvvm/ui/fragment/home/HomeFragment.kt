package com.Mvvm.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.Mvvm.adapter.Post_Adapter
import com.Mvvm.auth.authActivity.R
import com.Mvvm.auth.authActivity.databinding.FragmentHomeBinding
import com.Mvvm.base.BaseFragment
import com.Mvvm.netWork.Reposetory.UserRepo
import com.Mvvm.netWork.Resource
import com.Mvvm.netWork.UserApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.lang.Math.abs

class HomeFragment :BaseFragment<HomeViewModel,FragmentHomeBinding,UserRepo>() {


    lateinit var adapter:Post_Adapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPost()

    viewModel.user.observe(viewLifecycleOwner, Observer {
        when(it){
            is Resource.Success -> {

                adapter = Post_Adapter()
                binding.recyclerviewHome.adapter = adapter
                adapter.setPost(it.value.posts ,requireActivity())

            }
            is Resource.Failure -> {
                Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
                Log.e("posts", it.toString())
            if (it.errorCode == 401){
                Toast.makeText(requireActivity(), it.errorBody!!.string(), Toast.LENGTH_SHORT).show()
            }
            }
        }
    })

    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater, container,false)

    override fun getFragmentRepository() :UserRepo {
     val token = runBlocking { userPreferences.authToken.first() }
       val api = remoteDataSource.buildApi(UserApi::class.java , token.toString())
return UserRepo(api)
    }


}