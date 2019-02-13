package com.vikas.android_mvvm.models.services

import com.vikas.android_mvvm.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitAdapter {
    @Provides
    @Singleton
    fun getRemoteServiceInterface() : RemoteServiceInterface{
        return getRetrofit().create(RemoteServiceInterface::class.java)
    }
    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL).client(getOkhttp()).addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun getOkhttp() : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getHttpLoggingInterceptor()).build()
    }
    private fun getHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}