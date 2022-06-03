package com.Mvvm.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.Mvvm.auth.authActivity.AuthActivity
import com.Mvvm.auth.authActivity.R
import com.Mvvm.ui.Activity.HomeActivity
import com.Mvvm.userPreferences.UserPreferences
import com.Mvvm.utils.startNewActivity

class MainActivity : AppCompatActivity() {

    lateinit var dataStoreManager: UserPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataStoreManager = UserPreferences(this)

        dataStoreManager.authToken.asLiveData().observe(this, androidx.lifecycle.Observer {

            var activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            var activity2 = HomeActivity::class.java
        startNewActivity(activity)
        })

    }
}