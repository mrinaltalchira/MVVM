package com.Mvvm.ui.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.Mvvm.auth.authActivity.R
import com.Mvvm.ui.fragment.explore.ExploreFragment
import com.Mvvm.ui.fragment.home.HomeFragment
import com.Mvvm.ui.fragment.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       var bottomNavBar:BottomNavigationView = findViewById(R.id.bottom_nav_bar)
//        openMainFragment(HomeFragment())


//        bottomNavBar.setOnItemSelectedListener {
//            when(it){
//                R.id.homeFragment -> openMainFragment(HomeFragment())
//                R.id.exploreFragment -> openMainFragment(ExploreFragment())
//                R.id.userFragment -> openMainFragment(UserFragment())
//            }
//        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3
        ) as NavHostFragment
        var navController = navHostFragment.navController
        bottomNavBar.setupWithNavController(navController)

    }
    private fun openMainFragment(fragment:Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView3, fragment)
        transaction.commit()
    }
}