package com.Mvvm.netWork

import com.Mvvm.auth.authActivity.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        private val BASE__URL = "https://mern-instaa.herokuapp.com/api/v2/"
    }


    fun <Apii> buildApi(apii: Class<Apii>
    , authToken:String? = null):Apii{

        return Retrofit.Builder()
            .baseUrl(BASE__URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain -> chain.proceed(chain.request().newBuilder().also { it.addHeader("cookie","token=$authToken") }.build())}
                    .also { client ->
                    if (BuildConfig.DEBUG){
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apii)
    }

}