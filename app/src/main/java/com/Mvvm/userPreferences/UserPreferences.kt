package com.Mvvm.userPreferences

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

private const val USER_PREFERENCES_NAME = "user_preferences"
class UserPreferences(
   var context: Context
){
    private val applicationContext = context.applicationContext

    suspend fun saveAuthToken(token:String){
        applicationContext.datastore.edit {
            it[TOKEN] = token
Log.d("tell","${it[TOKEN]}")
Log.d("me", token)
        }
    }

    val authToken:Flow<String?>
    get() = applicationContext.datastore.data.map {
        it[TOKEN]
    }

    companion object {
    private val Context.datastore:DataStore<Preferences> by preferencesDataStore(name =  USER_PREFERENCES_NAME)
        val TOKEN = stringPreferencesKey("TOKEN")
    }
}
