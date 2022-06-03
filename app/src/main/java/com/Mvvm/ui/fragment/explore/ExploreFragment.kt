package com.Mvvm.ui.fragment.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Mvvm.auth.authActivity.R
import com.Mvvm.auth.authActivity.databinding.FragmentExploreBinding
import com.Mvvm.base.BaseFragment
import com.Mvvm.netWork.Reposetory.BaseRepo
import com.Mvvm.netWork.Reposetory.UserRepo
import com.Mvvm.netWork.UserApi
import android.webkit.WebView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.Mvvm.ui.fragment.user.UserFragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class ExploreFragment : BaseFragment<ExploreViewModel,FragmentExploreBinding,BaseRepo>()
    {
        lateinit var storedVerificationId:String
        lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
        private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
        private lateinit var auth: FirebaseAuth;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth


        binding.btnCameara.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_userFragment)
        }


        binding.btnContact.setOnClickListener {
           var a = binding.tvContact.text.toString()
            login(a)
        }
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // This method is called when the verification is completed
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                findNavController().navigate(R.id.action_userFragment_to_exploreFragment)
            }

            // Called when verification is failed add log statement to see the exception
            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("GFG" , "onVerificationFailed  $e")
            }


            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("GFG","onCodeSent: $verificationId")
                storedVerificationId = verificationId
                resendToken = token

                val bundle:Bundle = bundleOf("storedVerificationId" to storedVerificationId)
                findNavController().navigate(R.id.action_exploreFragment_to_userFragment,bundle)

            }
        }

    }

    override fun getViewModel() = ExploreViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentExploreBinding.inflate(layoutInflater,container,false)

    override fun getFragmentRepository() =
    UserRepo(remoteDataSource.buildApi(UserApi::class.java))

        private fun login(number : String) {

            // get the phone number from edit text and append the country cde with it
            if (number.isNotEmpty()){
               var a ="+91$number"
                sendVerificationCode(a)
            }else{
                Toast.makeText(requireActivity(),"Enter mobile number", Toast.LENGTH_SHORT).show()
            }
        }


        private fun sendVerificationCode(number: String) {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(requireActivity()) // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            Log.d("GFG" , "Auth started")
        }

}