package com.Mvvm.utils

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.view.View
import androidx.fragment.app.Fragment
import com.Mvvm.auth.authFragment.login
import com.Mvvm.netWork.Resource
import com.google.android.material.snackbar.Snackbar


fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visiblee(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enabled(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message:String,action:(()->Unit)? = null){

    val snackbar = Snackbar.make(this,message, Snackbar.LENGTH_LONG)
    action?.let { snackbar.setAction("Retry"){
        it()
    }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry:(() -> Unit)? = null
){
when{
    failure.isNetworkError -> requireView().snackbar("Please check your connection",retry)
failure.errorCode == 401 -> {
    if (this is login){
        requireView().snackbar("You've entered incorrect email or password")
    }else{
        //@todo performance logout here
    }
}
    else -> {
    val error = failure.errorBody?.string().toString()
        requireView().snackbar(error)
    }


}


}